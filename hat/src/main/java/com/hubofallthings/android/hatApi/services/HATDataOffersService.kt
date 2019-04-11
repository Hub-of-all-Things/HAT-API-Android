package com.hubofallthings.android.hatApi.services

import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.managers.HATParserManager
import com.hubofallthings.android.hatApi.managers.ResultType
import com.hubofallthings.android.hatApi.objects.datadebits.HATDataDebitObject
import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferObject
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HATDataOffersService {
    // MARK: - Get available data offers

    /**
    Gets the available data offers based on the merchants requested

    - parameter userDomain: The user's domain
    - parameter userToken: The user's token
    - parameter merchants: The merchants to get the offers from
    - parameter succesfulCallBack: A function of type ([DataOfferObject], String?) -> Void, executed on a successful result
    - parameter failCallBack: A function of type (DataPlugError) -> Void, executed on an unsuccessful result
     */
    fun getAvailableDataOffers(userDomain: String, userToken: String, application: String, merchants: List<Pair<String, Any>>?, successfulCallBack: (List<DataOfferObject>, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url = "https://$userDomain/api/v2.6/applications/$application/proxy/api/v2/offers"
        val headers = mapOf("x-auth-token" to userToken)
        HATNetworkManager().getRequest(
                url,
                merchants,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        val json = r.json!!.content
                        doAsync {
                            val dataOfferObject = HATParserManager().jsonToObjectList(json, DataOfferObject::class.java)
                            uiThread {
                                successfulCallBack(dataOfferObject, r.token)
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
    // MARK: - Get available data offers with claims

    /**
    Gets the available data offers based on the merchants requested

    - parameter userDomain: The user's domain
    - parameter userToken: The user's token
    - parameter merchants: The merchants to get the offers from
    - parameter succesfulCallBack: A function of type ([DataOfferObject], String?) -> Void, executed on a successful result
    - parameter failCallBack: A function of type (DataPlugError) -> Void, executed on an unsuccessful result
     */
    fun getAvailableDataOffersWithClaims(userDomain: String, userToken: String, application: String, merchants: List<Pair<String, Any>>?, successfulCallBack: (List<DataOfferObject>, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url = "https://$userDomain/api/v2.6/applications/$application/proxy/api/v2/offersWithClaims"
        val headers = mapOf("x-auth-token" to userToken)
        HATNetworkManager().getRequest(
                url,
                merchants,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        val json = r.json!!.content
                        doAsync {
                            val dataOfferObject = HATParserManager().jsonToObjectList(json, DataOfferObject::class.java)
                            uiThread {
                                successfulCallBack(dataOfferObject, r.token)
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
    // MARK: - Claim offer

    /**
    Gets the available data plugs for the user to enable

    - parameter userDomain: The user's domain
    - parameter userToken: The user's token
    - parameter offerID: The offer id to claim
    - parameter succesfulCallBack: A function of type ([HATDataPlugObject]) -> Void, executed on a successful result
    - parameter failCallBack: A function of type (Void) -> Void, executed on an unsuccessful result
     */
    fun claimOffer(userDomain: String, userToken: String, offerID: String, successfulCallBack: (String, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url = "https://$userDomain/api/v2.6/applications/databuyer/proxy/api/v2/offer/$offerID/claim"
        val headers = mapOf("x-auth-token" to userToken)
        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        val json = r.json
                        doAsync {
                            if (json?.array()?.length()!! > 0) {
                                val jObject = json.array().getJSONObject(0)
                                val dataDebitId = jObject.getString("dataDebitId")
//                            val dataOfferObject = HATParserManager().jsonToObjectList(json, DataOfferObject::class.java)
                                uiThread {
                                    if (!dataDebitId.isNullOrEmpty()) {
                                        successfulCallBack(dataDebitId, r.token)
                                    }
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

    // MARK: - Redeem offer

    /**
    Redeems cash offer

    - parameter userDomain: The user's domain
    - parameter userToken: The user's token
    - parameter succesfulCallBack: A function to execute on successful response returning the server message and the renewed user's token
    - parameter failCallBack: A function to execute on failed response returning the error
     */
    fun redeemOffer(userDomain: String, userToken: String, successfulCallBack: (String, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url: String = "https://$userDomain/api/v2.6/applications/databuyer/proxy/api/v2/user/redeem/cash"
        val headers = mapOf("x-auth-token" to userToken)
        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        successfulCallBack("message", r.token)//todo
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
    // MARK: - Get Merchants

    /**
    Gets available merchants from HAT

    - parameter userToken: The users token
    - parameter userDomain: The user's domain name
    - parameter succesfulCallBack: A function to execute on successful response returning the merchants array and the renewed user's token
    - parameter failCallBack: A function to execute on failed response returning the error
     */
    fun getMerchants(userToken: String, userDomain: String, successfulCallBack: (List<String>, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url: String = "https://$userDomain/api/v2/data/dex/databuyer"

    }
}