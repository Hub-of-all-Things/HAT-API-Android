package com.hubofallthings.android.hatApi

import com.hubofallthings.android.hatApi.objects.extrernalapps.HATApplicationObject
import com.hubofallthings.android.hatApi.objects.extrernalapps.HATExternalAppsInfoDeveloperObject
import com.hubofallthings.android.hatApi.objects.extrernalapps.HATExternalAppsObject
import com.hubofallthings.android.hatApi.services.ExternalAppsService
import org.junit.Assert
import org.junit.Test

class HATTestExternalAppsService : ExternalAppsService{
    private val mockAppsObj = HATApplicationObject(HATExternalAppsObject("testapp",null,null, HATExternalAppsInfoDeveloperObject("","","",""),null,null,null))
    private val tokenReturn = "2592fsdfsdf348j0572v89jhf9dsyguisd"

    override fun getExternalApps(userToken: String, userDomain: String, completion: (List<HATApplicationObject>?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if(userToken.isEmpty()){
            val e = HATError()
            e.errorMessage = "empty token"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(listOf(mockAppsObj),tokenReturn)
        }
    }

    override fun getAppInfo(userToken: String, userDomain: String, applicationId: String, completion: (HATApplicationObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if(applicationId.isEmpty()){
            val e = HATError()
            e.errorMessage = "empty id"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(mockAppsObj,tokenReturn)
        }
    }

    override fun setUpApp(userToken: String, userDomain: String, applicationId: String, completion: (HATApplicationObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if(applicationId.isEmpty()){
            val e = HATError()
            e.errorMessage = "empty id"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(mockAppsObj,tokenReturn)
        }
    }

    override fun disableApplication(userToken: String, userDomain: String, applicationId: String, completion: (HATApplicationObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if(applicationId.isEmpty()){
            val e = HATError()
            e.errorMessage = "empty id"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(mockAppsObj,tokenReturn)
        }
    }
    @Test
    fun testExternalAppsServiceCompletion(){
        fun completion(list: List<HATApplicationObject>?, newToken: String?){
            val expectedId = mockAppsObj.application?.id
            val expectedToken = tokenReturn
            Assert.assertEquals(expectedId, list!![0].application?.id)
            Assert.assertEquals(expectedToken, newToken)
        }
        fun failcallback() {}
        getExternalApps("254tijrgiosdgjsdsdfkisd9dsyguisd","test.hubat.net",{ list: List<HATApplicationObject>?, s: String? -> completion(list,s)},{ error -> failcallback() })
    }

    @Test
    fun testExternalAppsServiceFailCallback(){
        fun completion() {}
        fun failcallback(error : HATError){
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty token", error.errorMessage)
        }
        getExternalApps("","test.hubat.net",{ list: List<HATApplicationObject>?, s: String? -> completion() },{ error -> failcallback(error)})
    }

    @Test
    fun testAppInfoServiceCompletion(){
        fun completion(appObj: HATApplicationObject?, newToken: String?){
            val expectedId = mockAppsObj.application?.id
            val expectedToken = tokenReturn
            Assert.assertEquals(expectedId, appObj?.application?.id)
            Assert.assertEquals(expectedToken, newToken)
        }
        fun failcallback() {}
        getAppInfo("254tijrgiosdgjsdsdfkisd9dsyguisd","test.hubat.net","testapp",{ list: HATApplicationObject?, s: String? -> completion(list,s)},{ error -> failcallback() })
    }

    @Test
    fun testAppInfoServiceFailCallback(){
        fun completion(){}
        fun failcallback(error : HATError){
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty id", error.errorMessage)
        }
        getAppInfo("254tijrgiosdgjsdsdfkisd9dsyguisd","test.hubat.net","",{ list: HATApplicationObject?, s: String? -> completion()},{ error -> failcallback(error)})
    }

    @Test
    fun testSetupAppServiceCompletion(){
        fun completion(appObj: HATApplicationObject?, newToken: String?){
            val expectedId = mockAppsObj.application?.id
            val expectedToken = tokenReturn
            Assert.assertEquals(expectedId, appObj?.application?.id)
            Assert.assertEquals(expectedToken, newToken)
        }
        fun failcallback(){}
        getAppInfo("254tijrgiosdgjsdsdfkisd9dsyguisd","test.hubat.net","testapp",{ list: HATApplicationObject?, s: String? -> completion(list,s)},{ _ -> failcallback()})
    }

    @Test
    fun testSetupAppServiceFailCallback(){
        fun completion(){}
        fun failcallback(error : HATError){
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty id", error.errorMessage)
        }
        setUpApp("254tijrgiosdgjsdsdfkisd9dsyguisd","test.hubat.net","",{ list: HATApplicationObject?, s: String? -> completion()},{ error -> failcallback(error)})
    }

    @Test
    fun testDisableAppServiceCompletion(){
        fun completion(appObj: HATApplicationObject?, newToken: String?){
            val expectedId = mockAppsObj.application?.id
            val expectedToken = tokenReturn
            Assert.assertEquals(expectedId, appObj?.application?.id)
            Assert.assertEquals(expectedToken, newToken)
        }
        fun failcallback(){}
        disableApplication("254tijrgiosdgjsdsdfkisd9dsyguisd","test.hubat.net","testapp",{ list: HATApplicationObject?, s: String? -> completion(list,s)},{ _ -> failcallback()})
    }

    @Test
    fun testDisableAppServiceFailCallback(){
        fun completion(){}
        fun failcallback(error : HATError){
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty id", error.errorMessage)
        }
        disableApplication("254tijrgiosdgjsdsdfkisd9dsyguisd","test.hubat.net","",{ _: HATApplicationObject?, _: String? -> completion()},{ error -> failcallback(error)})
    }

}