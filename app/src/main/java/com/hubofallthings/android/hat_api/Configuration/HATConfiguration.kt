package com.hubofallthings.android.hat_api.Configuration

class VerifiedDomains{

    fun verifiedHATDomains(): Array<String> {

        return arrayOf(".hubofallthings.net", ".hubat.net", "hat.direct", "savy.io")
    }
}

class ContentType {

    val json: String = "application/json"
    val plain: String = "text/plain"
}

class RequestHeaders {

    val xAuthToken: String = "x-auth-token"
    val tokenParamName: String = "token"
}

class TokenParameters {

    val userDomain: String = "iss"
    val application: String = "application"
}