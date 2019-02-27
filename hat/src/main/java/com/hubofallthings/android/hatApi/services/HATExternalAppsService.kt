package com.hubofallthings.android.hatApi.services

import android.support.annotation.UiThread
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
    fun getExternalByKind(kind : String ,userToken : String, userDomain : String, completion: ((List<HATApplicationObject>?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun getDataPlugs(userToken : String, userDomain : String, completion: ((List<HATApplicationObject>?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun getApps(userToken : String, userDomain : String, completion: ((List<HATApplicationObject>?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun getAppWithAppId(userToken : String, userDomain : String ,applicationId: String, completion: ((HATApplicationObject?, String?) -> Unit),failCallBack: ((HATError) -> Unit))
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
                            val json = r.json!!.content
                            doAsync {
                                val externalAppsObject = HATParserManager().jsonToObjectList(json, HATApplicationObject::class.java)
                                uiThread{
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
                        val json = r.json!!.content
                        doAsync {
                            val externalAppsObject = json.toKotlinObject<HATApplicationObject?>()
                            uiThread{
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
                        val json = r.json!!.content
                        doAsync {
                            val externalAppsObject = json.toKotlinObject<HATApplicationObject?>()
                            uiThread{
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
                        val json = r.json!!.content
                        doAsync {
                            val externalAppsObject = json.toKotlinObject<HATApplicationObject?>()
                            uiThread{
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
    // MARK: - Get external apps by Kind

    /**
    Get external apps by Kind

    - parameter kind: The application kind, required to complete this request
    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the apps and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun getExternalByKind(kind: String, userToken: String, userDomain: String, completion: (List<HATApplicationObject>?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        fun completionApps(appsList: List<HATApplicationObject>?, newToken: String?){
            val result = ArrayList<HATApplicationObject>()
            if(appsList!=null){
                doAsync {
                    for(i in appsList.indices) {
                        if (appsList[i].application?.kind?.kind == kind){
                            result.add(appsList[i])
                        }
                    }
                    uiThread{
                        completion(result,newToken)
                    }
                }
            } else {
                val e = HATError()
                e.errorMessage = "null list"
                e.errorCode = 403
                failCallBack(e)
            }
        }
        fun failcallback(error: HATError) {
            failCallBack(error)
        }
        HATExternalAppsService().getExternalApps(userToken,userDomain,{ list: List<HATApplicationObject>?, s: String? -> completionApps(list,s)},{ error -> failcallback(error) })
    }
    // MARK: - Get Data plugs

    /**
    Get Data plugs

    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the data plugs and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun getDataPlugs(userToken: String, userDomain: String, completion: (List<HATApplicationObject>?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        getExternalByKind("DataPlug", userToken, userDomain,{ list: List<HATApplicationObject>?, s: String? -> completion(list,s)},{ error -> failCallBack(error) })
    }
    // MARK: - Get Apps

    /**
    Get Apps

    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the data plugs and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun getApps(userToken: String, userDomain: String, completion: (List<HATApplicationObject>?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        getExternalByKind("App", userToken, userDomain,{ list: List<HATApplicationObject>?, s: String? -> completion(list,s)},{ error -> failCallBack(error) })
    }

    override fun getAppWithAppId(userToken: String, userDomain: String, applicationId: String, completion: (HATApplicationObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if (applicationId == "hatapp" || applicationId == "hatappstaging" ||applicationId == "notables" || applicationId == "datatraderstaging"  || applicationId == "shapestaging" ||applicationId == "datatrader"  || applicationId == "shape"){
            getAppInfo(userToken,userDomain,applicationId,{app,newToken -> completion(app,newToken)},{error -> failCallBack(error)})
        } else {
            fun completionApp(list : List<HATApplicationObject>?,newToken  : String?){
                doAsync {
                    if (applicationId.isNotEmpty() && list!=null){
                        var appObj : HATApplicationObject? = null
                        for (i in list.indices){
                            if(list[i].application?.id == applicationId){
                                appObj = list[i]
                                break
                            }
                        }
                        uiThread {
                            if(appObj != null){
                                completion(appObj,newToken)
                            } else {
                                val e = HATError()
                                e.errorMessage = "application is null"
                                e.errorCode = 400
                                failCallBack(e)
                            }
                        }
                    } else{
                        val e = HATError()
                        e.errorMessage = "app id or list is empty"
                        e.errorCode = 400
                        failCallBack(e)
                    }
                }
            }
            getExternalApps(userToken,userDomain,{list ,newToken -> completionApp(list,newToken)}, {error -> failCallBack(error)})
        }
    }
}