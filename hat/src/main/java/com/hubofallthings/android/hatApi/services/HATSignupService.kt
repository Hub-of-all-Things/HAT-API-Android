package com.hubofallthings.android.hatApi.services

import com.hubofallthings.android.hatApi.managers.HATNetworkManager

data class SignupValidationSuccess(
        val message: String
)

data class SignupValidationError(
        val message: String,
        val cause: String
)

class HATSignupService {
    // MARK: - Validate Signup Email

    /**
    Validates email address with the HAT

    - parameter baseUrl: The base url to validate with the HAT f.e https://hatters.hubat.net
    - parameter email: The email to validate with the HAT
    - parameter sandbox: If the application is sandbox or not
    - parameter successfulCallBack: A function to call if everything is ok and returns a message
    - parameter failCallBack: A function to call if fail
     */
    fun validateEmailAddress(baseUrl: String, email: String, sandbox: Boolean? = true, successfulCallBack: (SignupValidationSuccess) -> Unit, failCallBack: (SignupValidationError) -> Unit) {
        val url = "$baseUrl/api/validate-email"
        val parameters = listOf("email" to email, "sandbox" to sandbox.toString())

        HATNetworkManager().getRequest(url, parameters, null) {
            if (it?.statusCode == 200) {
                if (it.json == null) {
                    failCallBack(SignupValidationError("Email invalid", "Unknown error"))
                }

                it.json?.let { json ->
                    val message: String? = json.obj()["message"].toString()

                    if (!message.isNullOrEmpty()) {
                        successfulCallBack(SignupValidationSuccess(message))
                    } else {
                        failCallBack(SignupValidationError("Email invalid", "Unknown error"))
                    }
                }
            } else {
                it?.json?.let { json ->
                    val message: String? = json.obj()["message"].toString()
                    val cause: String? = json.obj()["cause"].toString()

                    failCallBack(SignupValidationError(message ?: "Email invalid", cause ?: "Unknown error"))

                }

                if (it?.json == null) {
                    failCallBack(SignupValidationError("Email invalid", "Unknown error"))
                }
            }
        }
    }

    /**
    Validates HAT address with HAT


    - parameter baseUrl: The base url to validate with the HAT f.e https://hatters.hubat.net
    - parameter name: The HAT name to validate with the HAT
    - parameter sandbox: If the application is sandbox or not
    - parameter successfulCallBack: A function to call if everything is ok and returns a message
    - parameter failCallBack: A function to call if fail
     */
    fun validateHATAddress(baseUrl: String, name: String, sandbox: Boolean? = true, successfulCallBack: (SignupValidationSuccess) -> Unit, failCallBack: (SignupValidationError) -> Unit) {
        val url = "$baseUrl/api/validate-hatname"
        val parameters = listOf("name" to name, "sandbox" to sandbox.toString())

        HATNetworkManager().getRequest(url, parameters, null) {
            if (it?.statusCode == 200) {
                if (it.json == null) {
                    failCallBack(SignupValidationError("HAT name invalid", "Unknown error"))
                }

                it.json?.let { json ->
                    val message: String? = json.obj()["message"].toString()

                    if (!message.isNullOrEmpty()) {
                        successfulCallBack(SignupValidationSuccess(message))
                    } else {
                        failCallBack(SignupValidationError("HAT name invalid", "Unknown error"))
                    }
                }
            } else {
                it?.json?.let { json ->
                    val message: String? = json.obj()["message"].toString()
                    val cause: String? = json.obj()["cause"].toString()

                    failCallBack(SignupValidationError(message ?: "HAT name invalid", cause ?: "Unknown error"))

                }

                if (it?.json == null) {
                    failCallBack(SignupValidationError("HAT name invalid", "Unknown error"))
                }
            }
        }
    }
}