package com.example.whiteshadow.hat_api.Services

import com.example.whiteshadow.hat_api.Configuration.VerifiedDomains
import com.example.whiteshadow.hat_api.HATError
import java.net.URL

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
                                url: URL,
                                success: ((String?, String?) -> Void)?,
                                failed: ((HATError) -> Void)?) {



    }
}