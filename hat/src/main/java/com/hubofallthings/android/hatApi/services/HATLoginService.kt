package com.hubofallthings.android.hatApi.services

import com.hubofallthings.android.hatApi.managers.HATNetworkManager

data class LoginResolveSuccess(
        val hatName: String,
        val hatCluster: String
)

data class LoginResolveError(
        val message: String,
        val cause: String
)

class HATLoginService {
    // MARK: - Validate Data

    /**
    Resolves Email address with the HAT

    - parameter baseUrl: The base url to validate with the HAT f.e https://hatters.hubat.net
    - parameter email: The email to validate with the HAT
    - parameter sandbox: If the application is sandbox or not
    - parameter successfulCallBack: A function to call if everything is ok and returns hatName and hatCluster
    - parameter failCallBack: A function to call if fail
     */
    fun resolvesEmailAddress(baseUrl: String, email: String, sandbox: Boolean? = false, successfulCallBack: (LoginResolveSuccess) -> Unit, failCallBack: (LoginResolveError) -> Unit) {
        val url = "$baseUrl/api/resolve-email"
        val parameters = listOf("email" to email, "sandbox" to sandbox.toString())

        HATNetworkManager().getRequest(url, parameters, null) {
            if (it?.statusCode == 200) {
                if (it.json == null) {
                    failCallBack(LoginResolveError("Not found", "Email not found"))
                }

                it.json?.let { json ->
                    val hatCluster: String? = json.obj()["hatCluster"].toString()
                    val hatName: String? = json.obj()["hatName"].toString()

                    if (!hatName.isNullOrEmpty() && !hatCluster.isNullOrEmpty()) {
                        successfulCallBack(LoginResolveSuccess(hatName, hatCluster))
                    } else {
                        failCallBack(LoginResolveError("Not found", "Email not found"))
                    }
                }
            } else {
                it?.json?.let { json ->
                    val message: String? = json.obj()["message"].toString()
                    val cause: String? = json.obj()["cause"].toString()

                    failCallBack(LoginResolveError(message ?: "Not found", cause ?: "Unexpected error"))
                }

                if (it?.json == null) {
                    failCallBack(LoginResolveError("Not found", "Email not found"))
                }
            }
        }
    }

    /**
    Resolves HAT address with HAT


    - parameter baseUrl: The base url to validate with the HAT f.e https://hatters.hubat.net
    - parameter name: The hat name to validate with the HAT
    - parameter sandbox: If the application is sandbox or not
    - parameter successfulCallBack: A function to call if everything is ok and returns hatName and hatCluster
    - parameter failCallBack: A function to call if fail
     */
    fun resolveHATAddress(baseUrl: String, name: String, sandbox: Boolean? = false, successfulCallBack: (LoginResolveSuccess) -> Unit, failCallBack: (LoginResolveError) -> Unit) {
        val url: String = "$baseUrl/api/resolve-hatname"
        val parameters = listOf("name" to name, "sandbox" to sandbox.toString())

        HATNetworkManager().getRequest(url, parameters, null) {
            if (it?.statusCode == 200) {
                if (it.json == null) {
                    failCallBack(LoginResolveError("Not found", "Email not found"))
                }

                it.json?.let { json ->
                    val hatCluster: String? = json.obj()["hatCluster"].toString()
                    val hatName: String? = json.obj()["hatName"].toString()

                    if (!hatName.isNullOrEmpty() && !hatCluster.isNullOrEmpty()) {
                        successfulCallBack(LoginResolveSuccess(hatName, hatCluster))
                    } else {
                        failCallBack(LoginResolveError("Not found", "Email not found"))
                    }
                }
            } else {
                it?.json?.let { json ->
                    val message: String? = json.obj()["message"].toString()
                    val cause: String? = json.obj()["cause"].toString()
                    failCallBack(LoginResolveError(message ?: "Not found", cause ?: "Unexpected error"))
                }

                if (it?.json == null) {
                    failCallBack(LoginResolveError("Not found", "Email not found"))
                }
            }
        }
    }

}