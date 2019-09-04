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

package com.hubofallthings.android.hatApi.services

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hubofallthings.android.hatApi.configuration.ContentType
import com.hubofallthings.android.hatApi.configuration.TokenParameters
import com.hubofallthings.android.hatApi.configuration.VerifiedDomains
import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.managers.HATParserManager
import com.hubofallthings.android.hatApi.managers.ResultType
import com.hubofallthings.android.hatApi.objects.purchase.PurchaseObject
import com.hubofallthings.android.hatApi.objects.systemStatus.HATSystemStatusObject
import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.JWT
import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import java.io.StringReader
import java.security.interfaces.RSAPublicKey
import org.bouncycastle.util.io.pem.PemReader
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec

/**
 * Created by whiteshadow on 23/3/18.
 */
open class HATService {

    fun formatAndVerifyDomain(
            userHATDomain: String,
            verifiedDomains: Array<String> = VerifiedDomains().verifiedHATDomains(),
            successfulVerification: (String) -> Void,
            failedVerification: (String) -> Void
    ) {

        val trimmedString: String = userHATDomain.trim()
        val isAllowedDomain: Boolean = verifiedDomains.contains(trimmedString)

        if (isAllowedDomain) {

            successfulVerification(userHATDomain)
        } else {

            val message: String = "The domain you entered is incorrect. Accepted domains are 'hubofallthings.net, hat.direct, hubat.net and savy.io. Please correct any typos and try again"
            failedVerification(message)
        }
    }


    fun loginToHATAuthorization(
            applicationName: String,
            url: String,
            success: ((String?, String?) -> Unit)?,
            failed: ((HATError) -> Unit)?
    ) {

        val token = HATNetworkManager().getQueryStringParameter(url, "token")
        if (!token.isNullOrEmpty()) {

            val decodedToken = JWTParser.parse(token)
            val userDomain = decodedToken.jwtClaimsSet.getClaim(TokenParameters().userDomain).toString()
            val headers = mapOf("Accept" to ContentType().plain, "Content-Type" to ContentType().plain)
            val encodedUrl = this.getPublicKeyURL(userDomain)

            HATNetworkManager().getRequestString(encodedUrl, null, headers) { r ->
                this.verifyToken(r, token, applicationName, decodedToken, userDomain, success, failed)
            }
        }

    }

    fun verifyToken(
            r: ResultType?,
            token: String,
            applicationName: String,
            decodedToken: JWT,
            userDomain: String,
            success: ((String?, String?) -> Unit)?,
            failed: ((HATError) -> Unit)?
    ) {
        when (r) {

            ResultType.IsSuccess -> {

                if (decodedToken.jwtClaimsSet.issuer.isNullOrEmpty()) {

                    val error = HATError()
                    error.errorMessage = "No issuer found in token"
                    error.errorCode = 401
                    failed?.invoke(error)
                    return@verifyToken
                } else {

                    val appName = decodedToken.jwtClaimsSet.getClaim("application")

                    if (appName == null) {

                        val error = HATError()
                        error.errorMessage = "No application or accessScope claim has been found in token"
                        error.errorCode = 401
                        failed?.invoke(error)
                        return@verifyToken
                    }

                    if (!r.resultString.isNullOrEmpty()) {

                        success?.invoke(userDomain, token)

                        val key = readPublicKey(r.resultString!!)

                        try {

                            val cSignedJWT = SignedJWT.parse(token)
                            val verifier = RSASSAVerifier(key)
                            cSignedJWT.verify(verifier)
                            success?.invoke(userDomain, token)
                        } catch (error: JOSEException) { }
                    } else {

                        val error = HATError()
                        error.errorMessage = "No public key was found"
                        error.errorCode = 401
                        failed?.invoke(error)
                        return@verifyToken
                    }
                }
            }
            ResultType.HasFailed -> {

                val error = HATError()
                error.errorMessage = "No public key was found"
                error.errorCode = 401
                failed?.invoke(error)
                return@verifyToken
            }
        }
    }

    fun readPublicKey(publicKey: String): RSAPublicKey {

        val reader = PemReader(StringReader(publicKey))
        val keyBytes = reader.readPemObject().content

        val kf = KeyFactory.getInstance("RSA")
        val keySpec = X509EncodedKeySpec(keyBytes)

        return kf.generatePublic(keySpec) as RSAPublicKey
    }

    private fun getPublicKeyURL(userDomain: String): String {
        return "https://$userDomain/publickey"
    }
    // MARK: - Purchase

    /**
    Confirms the hat purchase

    - parameter purchaseModel: The PurchaseObject to send to HAT
    - parameter succesfulCallBack: A function to call if everything is ok
    - parameter failCallBack: A function to call if fail
     */
    fun confirmHATPurchase(purchaseModel: PurchaseObject, successfulCallBack: (String, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url = "https://hatters.hubofallthings.com/api/products/hat/purchase"
        val mapper = jacksonObjectMapper()
        val purchaseJson = mapper.writeValueAsString(purchaseModel)
        HATNetworkManager().postRequest(url, purchaseJson, mapOf("Content-Type" to "application/json")) {
            if (it?.statusCode == 200) {
                successfulCallBack("result ok", "")
            } else {
                val e = HATError()
                e.errorMessage = it?.resultString
                e.errorCode = it?.statusCode
                failCallBack(e)
            }
        }
    }

    // MARK: - Get system status

    /**
    Fetches all the info related to user's HAT

    - parameter userDomain: The user's domain
    - parameter userToken: The user's token
    - parameter succesfulCallBack: A function to call if everything is ok
    - parameter failCallBack: A function to call if fail
     */
    fun getSystemStatus(userDomain: String, userToken: String, completion: (List<HATSystemStatusObject>, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url: String = "https://$userDomain/api/v2.6/system/status"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        val json = r.json?.content
                        doAsync {
                            json?.let { jsonString ->
                                val hatFeedObject = HATParserManager().jsonToObjectList(jsonString, HATSystemStatusObject::class.java)
                                uiThread {
                                    completion(hatFeedObject, r.token)
                                }
                            }
                        }
                    }
                }
                ResultType.HasFailed -> {
                    val error = HATError()
                    error.errorCode = r.statusCode
                    error.errorMessage = r.resultString
                    failCallBack(error)
                }
                null -> {
                }
            }
        }
    }
}