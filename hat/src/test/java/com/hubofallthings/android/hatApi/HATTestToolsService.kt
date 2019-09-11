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

import com.hubofallthings.android.hatApi.objects.extrernalapps.HATExternalAppsIllustrationObject
import com.hubofallthings.android.hatApi.objects.extrernalapps.HATExternalAppsInfoDescriptionObject
import com.hubofallthings.android.hatApi.objects.extrernalapps.HATExternalAppsInfoDeveloperObject
import com.hubofallthings.android.hatApi.objects.tools.*
import com.hubofallthings.android.hatApi.services.ToolsService
import org.junit.Assert
import org.junit.Test

class HATTestToolsService : ToolsService {
     private val mockToolsObj = HATToolsObject("test-tool",
         HATToolsInfoObject("1.0.0", "", "", "",
             HATExternalAppsInfoDescriptionObject(null, null, null), "", "", null,
             HATToolsGraphicsObject(HATExternalAppsIllustrationObject(null, "", null),
                 arrayOf(HATExternalAppsIllustrationObject("", "", "", "")))),
         HATExternalAppsInfoDeveloperObject(),
         HATToolsStatusObject(available = true, enabled = true, executionStarted = "", lastExecution = ""),
         HATToolsDataBundleObject(name = "tool", bundle = emptyMap()),
         HATToolsTriggerObject(triggerType = "")
         )
    private val tokenReturn = "2592fsdfsdf348j0572v89jhf9dsyguisd"

    override fun getAvailableTools(userToken: String, userDomain: String, completion: (List<HATToolsObject>?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if (userToken.isEmpty()) {
            val e = HATError()
            e.errorMessage = "empty token"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(listOf(mockToolsObj), tokenReturn)
        }
    }

    override fun getTool(toolName: String, userToken: String, userDomain: String, completion: (HATToolsObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if (toolName.isEmpty()) {
            val e = HATError()
            e.errorMessage = "empty id"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(mockToolsObj, tokenReturn)
        }
    }

    override fun enableTool(toolName: String, userToken: String, userDomain: String, completion: (HATToolsObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if (toolName.isEmpty()) {
            val e = HATError()
            e.errorMessage = "empty id"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(mockToolsObj, tokenReturn)
        }
    }

    override fun disableTool(toolName: String, userToken: String, userDomain: String, completion: (HATToolsObject?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if (toolName.isEmpty()) {
            val e = HATError()
            e.errorMessage = "empty id"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(mockToolsObj, tokenReturn)
        }
    }

    override fun triggerToolUpdate(toolName: String, userToken: String, userDomain: String, completion: (String?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if (toolName.isEmpty()) {
            val e = HATError()
            e.errorMessage = "empty id"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion("ok", tokenReturn)
        }
    }

    @Test
    fun testAvailableToolsCompletion() {
        fun completion(list: List<HATToolsObject>?, newToken: String?) {
            val expectedId = mockToolsObj.id
            val expectedToken = tokenReturn
            Assert.assertEquals(expectedId, list!![0].id)
            Assert.assertEquals(expectedToken, newToken)
        }

        fun failcallback() {}
        getAvailableTools("254tijrgiosdgjsdsdfkisd9dsyguisd", "test.hubat.net", { list: List<HATToolsObject>?, s: String? -> completion(list, s) }, { failcallback() })
    }

    @Test
    fun testAvailableToolsFallback() {
        fun completion() {}
        fun failcallback(error: HATError) {
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty token", error.errorMessage)
        }
        getAvailableTools("", "test.hubat.net", { _: List<HATToolsObject>?, _: String? -> completion() }, { error -> failcallback(error) })
    }

    @Test
    fun testToolCompletion() {
        fun completion(list: HATToolsObject?, newToken: String?) {
            val expectedId = mockToolsObj.id
            val expectedToken = tokenReturn
            Assert.assertEquals(expectedId, list?.id)
            Assert.assertEquals(expectedToken, newToken)
        }

        fun failcallback() {}
        getTool("test-tool", "toekekekekekensda", "test.hubat.net", { list: HATToolsObject?, s: String? -> completion(list, s) }, { failcallback() })
    }

    @Test
    fun testToolFallback() {
        fun completion() {}
        fun failcallback(error: HATError) {
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty id", error.errorMessage)
        }
        getTool("test-tool", "toekekekekekensda", "test.hubat.net", { _: HATToolsObject?, _: String? -> completion() }, { error -> failcallback(error) })
    }

    @Test
    fun testEnableToolCompletion() {
        fun completion(list: HATToolsObject?, newToken: String?) {
            val expectedId = mockToolsObj.id
            val expectedToken = tokenReturn
            Assert.assertEquals(expectedId, list?.id)
            Assert.assertEquals(expectedToken, newToken)
        }

        fun failcallback() {}
        enableTool("test-tool", "tijrgiosdgjsdsdfkisd9dsyguisd", "test.hubat.net", { list: HATToolsObject?, s: String? -> completion(list, s) }, { failcallback() })
    }

    @Test
    fun testEnableToolFallback() {
        fun completion() {}
        fun failcallback(error: HATError) {
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty id", error.errorMessage)
        }
        enableTool("test-tool", "tijrgiosdgjsdsdfkisd9dsyguisd", "test.hubat.net", { _: HATToolsObject?, _: String? -> completion() }, { error -> failcallback(error) })
    }

    @Test
    fun testdisableToolCompletion() {
        fun completion(list: HATToolsObject?, newToken: String?) {
            val expectedId = mockToolsObj.id
            val expectedToken = tokenReturn
            Assert.assertEquals(expectedId, list?.id)
            Assert.assertEquals(expectedToken, newToken)
        }

        fun failcallback() {}
        disableTool("test-tool", "tijrgiosdgjsdsdfkisd9dsyguisd", "test.hubat.net", { list: HATToolsObject?, s: String? -> completion(list, s) }, { failcallback() })
    }

    @Test
    fun testdisableToolFallback() {
        fun completion() {}
        fun failcallback(error: HATError) {
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty id", error.errorMessage)
        }
        disableTool("test-tool", "tijrgiosdgjsdsdfkisd9dsyguisd", "test.hubat.net", { _: HATToolsObject?, _: String? -> completion() }, { error -> failcallback(error) })
    }

    @Test
    fun testTriggerToolCompletion() {
        fun completion(result: String?, newToken: String?) {
            val expectedToken = tokenReturn
            Assert.assertEquals("ok", result)
            Assert.assertEquals(expectedToken, newToken)
        }

        fun failcallback() {}
        triggerToolUpdate("test-tool", "tijrgiosdgjsdsdfkisd9dsyguisd", "test.hubat.net", { res: String?, s: String? -> completion(res, s) }, { failcallback() })
    }

    @Test
    fun testTriggerToolFallback() {
        fun completion() {}
        fun failcallback(error: HATError) {
            Assert.assertEquals(403, error.errorCode)
            Assert.assertEquals("empty id", error.errorMessage)
        }
        triggerToolUpdate("test-tool", "tijrgiosdgjsdsdfkisd9dsyguisd", "test.hubat.net", { _: String?, _: String? -> completion() }, { error -> failcallback(error) })
    }
}
