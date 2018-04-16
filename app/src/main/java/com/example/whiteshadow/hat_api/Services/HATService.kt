package com.example.whiteshadow.hat_api.Services

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.RSA256
import com.example.whiteshadow.hat_api.Configuration.ContentType
import com.example.whiteshadow.hat_api.Configuration.RequestHeaders
import com.example.whiteshadow.hat_api.Configuration.TokenParameters
import com.example.whiteshadow.hat_api.Configuration.VerifiedDomains
import com.example.whiteshadow.hat_api.HATError
import com.example.whiteshadow.hat_api.Managers.HATFormatterManager
import com.example.whiteshadow.hat_api.Managers.HATNetworkManager
import com.example.whiteshadow.hat_api.Managers.ResultType
import java.io.StringReader
import java.net.URLEncoder
import java.security.PublicKey
import java.security.interfaces.RSAPublicKey
import org.bouncycastle.util.io.pem.PemReader

import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec

/**
 * Created by whiteshadow on 23/3/18.
 */
class HATService {
    
    fun formatAndVerifyDomain(userHATDomain: String,
                              verifiedDomains: Array<String> = VerifiedDomains().verifiedHATDomains(),
                              successfulVerification: (String) -> Void,
                              failedVerification: (String) -> Void) {

        val trimmedString: String = userHATDomain.trim()
        val isAllowedDomain: Boolean = verifiedDomains.contains(trimmedString)

        if (isAllowedDomain) {

            successfulVerification(userHATDomain)
        } else {

            val message: String = "The domain you entered is incorrect. Accepted domains are 'hubofallthings.net, hat.direct, hubat.net and savy.io. Please correct any typos and try again"
            failedVerification(message)
        }
    }

    /**
     * 
     *
     * @param  
     * @return 
     */
    fun loginToHATAuthorization(applicationName: String,
                                url: String,
                                success: ((String?, String?) -> Void)?,
                                failed: ((HATError) -> Void)?) {

        val token = HATNetworkManager().getQueryStringParameter(url, RequestHeaders().xAuthToken)
        if (token != null) {

            val decodedToken = JWT.decode(token!!)
            val userDomain = decodedToken.getClaim(TokenParameters().userDomain).toString()
            val headers = listOf("Accept" to ContentType().plain, "Content-Type" to ContentType().plain)
            val encodedUrl = this.getPublicKeyURL(url)

            HATNetworkManager().getRequestString(encodedUrl, null, headers, {
                r ->

                when (r) {

                    ResultType.IsSuccess -> {

                        if (decodedToken.issuer == null) {

                            val error = HATError()
                            error.errorMessage = "No issuer found in token"
                            error.errorCode = 401
                            failed?.invoke(error)
                            return@getRequestString
                        } else {

                            val appName = decodedToken.getClaim("application").toString()
                            val accessScope = decodedToken.getClaim("accessScope").toString()


                            if (appName != applicationName && accessScope == "") {

                                val error = HATError()
                                error.errorMessage = "No application or accessScope claim has been found in token"
                                error.errorCode = 401
                                failed?.invoke(error)
                                return@getRequestString
                            } else if (accessScope != "owner" && appName == "") {

                                val error = HATError()
                                error.errorMessage = "No application or accessScope claim has been found in token"
                                error.errorCode = 401
                                failed?.invoke(error)
                                return@getRequestString
                            }

                            if (r.resultString != null) {

                                val key = readPublicKey(r.resultString!!) as RSAPublicKey?
                                val algorithm = RSA256(key, null)
                                algorithm.verify(decodedToken)

                                // MY code
                                val verifier = JWT.require(algorithm).build()
                                verifier.verify(token)


                                success?.invoke(userDomain, token)
                            } else {

                                val error = HATError()
                                error.errorMessage = "No public key was found"
                                error.errorCode = 401
                                failed?.invoke(error)
                                return@getRequestString
                            }

                        }
                    }
                    ResultType.HasFailed -> {

                    }
                }
            })
        }

    }

    private fun readPublicKey(publicKey: String): PublicKey {

        val reader = PemReader(StringReader(publicKey))
        val keyBytes = reader.readPemObject().content

        val kf = KeyFactory.getInstance("")
        val keySpec = X509EncodedKeySpec(keyBytes)
        return kf.generatePublic(keySpec)
    }

    private fun getPublicKeyURL(url: String): String {

        return "https://" + URLEncoder.encode(url, "utf-8") + "/publickey"
    }
}
