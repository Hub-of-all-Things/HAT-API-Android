package com.hubofallthings.android.hatApi.services

import android.util.Log
import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.managers.HATParserManager
import com.hubofallthings.android.hatApi.managers.ResultType.*
import com.hubofallthings.android.hatApi.managers.toKotlinObject
import com.hubofallthings.android.hatApi.objects.extrernalapps.HATApplicationObject
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface ExternalAppsService{
    fun getExternalApps(userToken : String, userDomain : String, completion: ((List<HATApplicationObject>?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun getAppInfo(userToken : String, userDomain : String ,applicationId: String, completion: ((HATApplicationObject?, String?) -> Unit),failCallBack: ((HATError) -> Unit))
    fun setUpApp(userToken : String, userDomain : String ,applicationId: String, completion: ((HATApplicationObject?, String?) -> Unit),failCallBack: ((HATError) -> Unit))
    fun disableApplication(userToken : String, userDomain : String ,applicationId: String, completion: ((HATApplicationObject?, String?) -> Unit),failCallBack: ((HATError) -> Unit))
}
class HATExternalAppsService : ExternalAppsService {
    // MARK: - Get external apps

    /**
    Gets the apps from HAT

    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the apps and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun getExternalApps(userToken : String, userDomain : String , completion: ((List<HATApplicationObject>?, String?) -> Unit),failCallBack: ((HATError) -> Unit)){
        val url = "https://$userDomain/api/v2.6/applications"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
                when(r){
                    IsSuccess -> {
                        if (r.statusCode != 401) {
                            Log.i("externalApps","success")
                            val json = r.json!!.content
                            doAsync {
                                val externalAppsObject = HATParserManager().jsonToObjectList(json, HATApplicationObject::class.java)
                                uiThread{
                                    Log.i("externalApps","completion")
                                    completion(externalAppsObject,r.token)
                                }
                            }
                        }
                    }
                    HasFailed -> {
                        val error = HATError()
                        error.errorCode = r.statusCode
                        error.errorMessage = r.resultString
                        failCallBack(error)
                    }
                    null -> {}
                }
        }
    }

    // MARK: - Get external apps

    /**
    Gets the apps from HAT

    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the apps and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun getAppInfo(userToken : String, userDomain : String ,applicationId: String, completion: ((HATApplicationObject?, String?) -> Unit),failCallBack: ((HATError) -> Unit)){
        val url = "https://$userDomain/api/v2.6/applications/$applicationId"
        val headers = mapOf("x-auth-token" to userToken, "Cache-Control" to "no-cache")

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when(r){
                IsSuccess -> {
                    if (r.statusCode != 401) {
                        Log.i("externalApps","success")
                        val json = r.json!!.content
                        doAsync {
                            val externalAppsObject = json.toKotlinObject<HATApplicationObject?>()
                            uiThread{
                                Log.i("externalApps","completion")
                                completion(externalAppsObject,r.token)
                            }
                        }
                    }
                }
                HasFailed -> {
                    val error = HATError()
                    error.errorCode = r.statusCode
                    error.errorMessage = r.resultString
                    failCallBack(error)
                }
                null -> {}
            }
        }
    }
    // MARK: - Setup external apps

    /**
    Sets up the app

    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter applicationID: The application id, required to complete this request
    - parameter completion: A function to execute on success with the apps and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun setUpApp(userToken : String, userDomain : String ,applicationId: String, completion: ((HATApplicationObject?, String?) -> Unit),failCallBack: ((HATError) -> Unit)){
        val url = "https://$userDomain/api/v2.6/applications/$applicationId/setup"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when(r){
                IsSuccess -> {
                    if (r.statusCode != 401) {
                        Log.i("externalApps","success")
                        val json = r.json!!.content
                        doAsync {
                            val externalAppsObject = json.toKotlinObject<HATApplicationObject?>()
                            uiThread{
                                Log.i("externalApps","completion")
                                completion(externalAppsObject,r.token)
                            }
                        }
                    }
                }
                HasFailed -> {
                    val error = HATError()
                    error.errorCode = r.statusCode
                    error.errorMessage = r.resultString
                    failCallBack(error)
                }
                null -> {}
            }
        }
    }
    // MARK: - Disable app

    /**
    Disable the app

    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter applicationID: The application id, required to complete this request
    - parameter completion: A function to execute on success with the apps and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun disableApplication(userToken : String, userDomain : String ,applicationId: String, completion: ((HATApplicationObject?, String?) -> Unit),failCallBack: ((HATError) -> Unit)){
        val url = "https://$userDomain/api/v2.6/applications/$applicationId/disable"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when(r){
                IsSuccess -> {
                    if (r.statusCode != 401) {
                        Log.i("externalApps","success")
                        val json = r.json!!.content
                        doAsync {
                            val externalAppsObject = json.toKotlinObject<HATApplicationObject?>()
                            uiThread{
                                Log.i("externalApps","completion")
                                completion(externalAppsObject,r.token)
                            }
                        }
                    }
                }
                HasFailed -> {
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