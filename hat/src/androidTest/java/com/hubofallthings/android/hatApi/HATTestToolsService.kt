/*
 * *
 *  * Copyright (C) 2018-2019 DataSwift Ltd
 *  *
 *  * SPDX-License-Identifier: MPL2
 *  *
 *  * This file is part of the Hub of All Things project (HAT).
 *  *
 *  * This Source Code Form is subject to the terms of the Mozilla Public
 *  * License, v. 2.0. If a copy of the MPL was not distributed with this
 *  * file, You can obtain one at http://mozilla.org/MPL/2.0/
 *
 */

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