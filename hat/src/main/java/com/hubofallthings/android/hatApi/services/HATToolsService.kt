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

package com.hubofallthings.android.hatApi.services

import android.util.Log
import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.managers.HATParserManager
import com.hubofallthings.android.hatApi.managers.ResultType
import com.hubofallthings.android.hatApi.managers.toKotlinObject
import com.hubofallthings.android.hatApi.objects.tools.HATToolsObject
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

interface ToolsService {
    fun getAvailableTools(userToken: String, userDomain: String, completion: ((List<HATToolsObject>?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun getTool(toolName: String, userToken: String, userDomain: String, completion: ((HATToolsObject?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun enableTool(toolName: String, userToken: String, userDomain: String, completion: ((HATToolsObject?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun disableTool(toolName: String, userToken: String, userDomain: String, completion: ((HATToolsObject?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
    fun triggerToolUpdate(toolName: String, userToken: String, userDomain: String, completion: ((String?, String?) -> Unit), failCallBack: ((HATError) -> Unit))
}
class HATToolsService : ToolsService {
    // MARK: - Get tools

    /**
    Gets the tools from HAT

    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the tools and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun getAvailableTools(userToken: String, userDomain: String, completion: ((List<HATToolsObject>?, String?) -> Unit), failCallBack: ((HATError) -> Unit)) {
        val url = "https://$userDomain/api/v2.6/she/function"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        Log.i("HATTools", "success")
                        val json = r.json?.content
                        doAsync {
                            val toolObj = HATParserManager().jsonToObjectList(json, HATToolsObject::class.java)
                            uiThread {
                                Log.i("HATTools", "completion")
                                completion(toolObj, r.token)
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

    /**
    Gets the tool from HAT

    - parameter toolName: The tool's name, required to complete this request
    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the tool and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun getTool(toolName: String, userToken: String, userDomain: String, completion: ((HATToolsObject?, String?) -> Unit), failCallBack: ((HATError) -> Unit)) {
        val url = "https://$userDomain/api/v2.6/she/function/$toolName"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        Log.i("HATTools", "success")
                        val json = r.json?.content
                        doAsync {
                            val toolObj = json?.toKotlinObject<HATToolsObject?>()
                            uiThread {
                                Log.i("HATTools", "completion")
                                completion(toolObj, r.token)
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

    /**
    Enable tool from HAT

    - parameter toolName: The tool's name, required to complete this request
    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the tool and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun enableTool(toolName: String, userToken: String, userDomain: String, completion: ((HATToolsObject?, String?) -> Unit), failCallBack: ((HATError) -> Unit)) {
        val url = "https://$userDomain/api/v2.6/she/function/$toolName/enable"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        Log.i("HATTools", "success")
                        val json = r.json?.content
                        doAsync {
                            val externalAppsObject = json?.toKotlinObject<HATToolsObject?>()
                            uiThread {
                                Log.i("HATTools", "completion")
                                completion(externalAppsObject, r.token)
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

    /**
    Disable tool from HAT

    - parameter toolName: The tool's name, required to complete this request
    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the tool and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun disableTool(toolName: String, userToken: String, userDomain: String, completion: ((HATToolsObject?, String?) -> Unit), failCallBack: ((HATError) -> Unit)) {
        val url = "https://$userDomain/api/v2.6/she/function/$toolName/disable"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        Log.i("HATTools", "success")
                        val json = r.json?.content
                        doAsync {
                            val externalAppsObject = json?.toKotlinObject<HATToolsObject?>()
                            uiThread {
                                Log.i("HATTools", "completion")
                                completion(externalAppsObject, r.token)
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

    /**
    Trigger tool update from HAT

    - parameter toolName: The tool's name, required to complete this request
    - parameter userToken: The user's token, required to complete this request
    - parameter userDomain: The user's domain, required to complete this request
    - parameter completion: A function to execute on success with the result message and the new token
    - parameter failCallBack: A function to execute on fail that takes the error produced
     */
    override fun triggerToolUpdate(toolName: String, userToken: String, userDomain: String, completion: ((String?, String?) -> Unit), failCallBack: ((HATError) -> Unit)) {
        val url = "https://$userDomain/api/v2.6/she/function/$toolName/trigger"
        val headers = mapOf("x-auth-token" to userToken)

        HATNetworkManager().getRequest(
                url,
                null,
                headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401) {
                        Log.i("HATTools", "success")
                        completion(r.resultString, r.token)
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
}