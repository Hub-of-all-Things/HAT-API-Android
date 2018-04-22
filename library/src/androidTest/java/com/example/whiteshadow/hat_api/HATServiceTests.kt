package com.example.whiteshadow.hat_api

import com.auth0.android.jwt.JWT
import com.example.whiteshadow.hat_api.Managers.ResultType
import com.example.whiteshadow.hat_api.Services.HATService
import org.junit.Test

class HATServiceTests {

    @Test
    fun testVerifyToken() {

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
        val decodedToken = JWT(token)
        var userDomain = "testing.hubat.net"
        var test = ResultType.IsSuccess
        test.statusCode = 200
        test.error = null
        test.json = null
        test.resultString = publicKey
        test.token = "token"

        fun successTest(userDomain: String?, newToken: String?): Unit {

            assert(true)
        }

        fun failedTest(error: HATError): Unit {

            assert(false)
        }

        HATService().verifyToken(
                test,
                token,
                "hatapp",
                decodedToken,
                userDomain,
                {userDomain, newToken-> successTest(userDomain, newToken)},
                { error1 -> failedTest(error1)

        })

    }
}