package com.hubofallthings.android.hatApi

import com.hubofallthings.android.hatApi.objects.tools.HATToolsObject
import com.hubofallthings.android.hatApi.services.ToolsService

class HATTestToolsService : ToolsService {
    // private val mockToolsObj = HATToolsObject("test-tool", HATToolsInfoObject("1.0.0","","","", HATExternalAppsInfoDescriptionObject(null,null,null),"","",null, HATToolsGraphicsObject(HATExternalAppsIllustrationObject(null,"",null),arrayOf(HATExternalAppsIllustrationObject("","","","")))),)
    private val tokenReturn = "2592fsdfsdf348j0572v89jhf9dsyguisd"

    override fun getAvailableTools(userToken: String, userDomain: String, completion: (List<HATToolsObject>?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getTool(toolName: String, userToken: String, userDomain: String, completion: (HATToolsObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun enableTool(toolName: String, userToken: String, userDomain: String, completion: (HATToolsObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun disableTool(toolName: String, userToken: String, userDomain: String, completion: (HATToolsObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun triggerToolUpdate(toolName: String, userToken: String, userDomain: String, completion: (String?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}