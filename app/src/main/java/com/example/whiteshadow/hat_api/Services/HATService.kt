package com.example.whiteshadow.hat_api.Services

import com.example.whiteshadow.hat_api.Configuration.ContentType
import com.example.whiteshadow.hat_api.Configuration.TokenParameters
import com.example.whiteshadow.hat_api.Configuration.VerifiedDomains
import com.example.whiteshadow.hat_api.HATError
import com.example.whiteshadow.hat_api.Managers.HATNetworkManager
import com.example.whiteshadow.hat_api.Managers.ResultType
import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.crypto.RSASSAVerifier
import com.nimbusds.jwt.JWT
import com.nimbusds.jwt.JWTParser
import com.nimbusds.jwt.SignedJWT
import java.io.StringReader
import java.net.URLEncoder
import java.security.PublicKey
import java.security.interfaces.RSAPublicKey
import org.bouncycastle.util.io.pem.PemReader

import java.security.KeyFactory
import java.security.KeyPair
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyGenerator

/**
 * Created by whiteshadow on 23/3/18.
 */
open class HATService {

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


    fun loginToHATAuthorization(applicationName: String,
                                url: String,
                                success: ((String?, String?) -> Unit)?,
                                failed: ((HATError) -> Unit)?) {

        val token = HATNetworkManager().getQueryStringParameter(url, "token")
        if (!token.isNullOrEmpty()) {

            val decodedToken = JWTParser.parse(token)
            val userDomain = decodedToken.jwtClaimsSet.getClaim(TokenParameters().userDomain).toString()
            val headers = mapOf("Accept" to ContentType().plain, "Content-Type" to ContentType().plain)
            val encodedUrl = this.getPublicKeyURL(userDomain)

            HATNetworkManager().getRequestString(encodedUrl, null, headers, {
                r ->

                this.verifyToken(r, token!!, applicationName, decodedToken, userDomain, success, failed)
            })
        }

    }

    fun verifyToken(r: ResultType?,
                    token: String,
                    applicationName: String,
                    decodedToken: JWT,
                    userDomain: String,
                    success: ((String?, String?) -> Unit)?,
                    failed: ((HATError) -> Unit)?) {

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
                        } catch (error: JOSEException) {

                        }
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
}
