package com.hubofallthings.android.hatApi.services

import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.managers.ResultType
import com.hubofallthings.android.hatApi.managers.toKotlinObject
import com.hubofallthings.android.hatApi.objects.feed.HATFeedObject
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HATFeedService{
    // MARK: - Get feed

    /**
    Gets the she feed from HAT

    - parameter userDomain: The user's domain
    - parameter userToken: The user's token
    - parameter parameters: The parameters to pass in the request, default is empty
    - parameter successCallback: A function of type (List<HATFeedObject>?, String?) that executes on success
    - parameter failed: A function of type (HATTableError) that executes on failure
     */
    fun getFeed(userDomain : String, userToken : String, parameters : List<Pair<String, Any>>?, hatSuffix: String = "", completion: ((List<HATFeedObject>?, String?) -> Unit), failCallBack: ((HATError) -> Unit)){
        val url = "https://$userDomain/api/v2.6/she/feed$hatSuffix"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                parameters,
                headers) { r ->
            when(r){
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        val json = r.json?.content
                        doAsync {
                            val hatFeedObject = json?.toKotlinObject<List<HATFeedObject>?>()
                            uiThread{
                                completion(hatFeedObject,r.token)
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
                null -> {}
            }
        }
    }
}