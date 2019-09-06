/*
 * *
 *  * Copyright (C) 2018-2019 DataSwift Ltd
 *  *
 *  * SPDX-License-Identifier: MPL2
 *  *
 *  * This file is part of the Hub of All Things project (HAT).
 *  *
 *  * This Source Code Form is subject to the terms of the Mozilla Public
 *  * License, v. 2.0. If a copy of the MPL was not distributed with this
 *  * file, You can obtain one at http://mozilla.org/MPL/2.0/
 *
 */

package com.hubofallthings.android.hatApi

import com.hubofallthings.android.hatApi.services.HATService
import com.hubofallthings.android.hatApi.managers.ResultType
import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse

class HATServiceTests {
    val publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmbpD4OytlMcWRuGHgfy8\n" +
            "q9I9GT46Sh3FwuZQkicCMqMWaV9ukwo6mW+e4UciNh5acXf2qEnQdbRHCxNVe90G\n" +
            "jTzVCPcFvigE/Tn9icNzISludwA4/uiAbaFwJg9dvXVphQuxZdq5dEIDAvPcqwUk\n" +
            "JBCX+CLBP1a0CWiB/ACbaVwYm2bZApZe52BLiw7ejvM6UvQoOjOYiRiVGJKdgUgm\n" +
            "WIruC+bMcbhbNpf/11M0+YCi/d51OSwup/nyEweoa6deTrMdFyMosnlcknEaWx9t\n" +
            "NPU3Agub9SNZVKkXTYgRXzoQu8k/BC331uKII1pi6atqLjQGkY4rY6fXJ3Db3NYI\n" +
            "9QIDAQAB\n" +
            "-----END PUBLIC KEY-----"
    val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxLWs4K1RUbml3TmlxbTVcL29NNFFBQlBIb1FDZ2Z4UVwvY2xHMDg0SWtVRkdLb0VidGQ2TGlKWkJMbU9pTzREcHJVVU5UaGlCdDgxU0JwTURUOHVNU1h4ZUVRbDc0THo0eWxUcEU4PSIsInJlc291cmNlIjoiaHR0cHM6XC9cL2RleC5odWJvZmFsbHRoaW5ncy5jb21cLyIsImFjY2Vzc1Njb3BlIjoidmFsaWRhdGUiLCJpc3MiOiJ0ZXN0aW5nLmh1YmF0Lm5ldCIsImV4cCI6MTUyMjE1NjcxMiwiaWF0IjoxNTE5NTY0NzEyLCJqdGkiOiI0MTk4MTNkNmY0YWU4MDEwMjNkYTQxYWMxNWMxMmUwMzllNjc3NjQwYjQwMjM1ZWEyYTYwNjdlYjcwYmJhNGEyY2YwODQ1OTIyN2RmYWNkMjg3MjU3MmQyYjU1NDg0MWE5NDI1NmMzZDcyZWMxNDgzNzUzYWVjYTM1M2I1MGI2YWRlZmU1MzlmMWFhN2E1MTA3OGZkMzk4YjQ5NDI2NzU4NmNkMzhkNzc1ZThlMjk5NDliNzFhNmU1YmVhOGNiNjJhODEwMGQwYzcwYjUyMDk3MzVhZDYzZjY5YTBiZjM0NWFkMThmM2U0Y2Y2MDQ3NTIyMzE4Njc0ZDYyZTYyM2Y4In0.Tbvr0ZWnIBzwTHYK-rSWjzz9aozyjKddKIZCm_cI2pWcecQdl83C6SWL1MUkFcH12So4YXqgkerv-zWuglNsBIXpdbhNuqVhK-Q7ro-JjmRFaS639fuwm1tCruAeAXINaeoXiLdH_gLL4SdFg8ilstwF8vGzOitbm0IkoPMnuV3Z5uhCUN-M56eVCIA_bl3dXspwhehl2alygVdjtnFy-xzXiMXHxZFhDqeR541oOdW8JSKVpSdgfEIyXt31NdOmpl42YeXQTirdB3kFNi2reVxHMoLqRziXFKa-kPa7yCxp0aWNo6cAvaZ1g6dS5lClgjnyFmr1YQS7VPxsnRMGzg"

    @Test
    fun testVerifyToken() {
        val decodedToken = JWTParser.parse(token)
        val userDomain = "testing.hubat.net"
        val test = ResultType.IsSuccess
        test.statusCode = 200
        test.error = null
        test.json = null
        test.resultString = publicKey
        test.token = "token"

        fun successTest() {
            assertTrue(true)
        }

        fun failedTest() {
            assertFalse(false)
        }

        HATService().verifyToken(
                test,
                token,
                "hatapp",
                decodedToken,
                userDomain,
                { _, _ -> successTest() },
                { failedTest() }
        )
    }

    @Test
    fun testReadPublicKey() {
        val publicKeyObject = HATService().readPublicKey(publicKey)

        try {

            val cSignedJWT = SignedJWT.parse(token)
            val verifier = RSASSAVerifier(publicKeyObject)
            cSignedJWT.verify(verifier)
            assertTrue(true)
        } catch (error: JOSEException) {

            assertFalse(false)
        }
    }
}
