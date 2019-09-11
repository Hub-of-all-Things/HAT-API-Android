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

import com.github.kittinunf.fuel.android.core.Json
import com.hubofallthings.android.hatApi.managers.NetworkLayer
import com.hubofallthings.android.hatApi.managers.ResultType
import org.junit.Test

class HATNetworkManagerMock : NetworkLayer {
    val mockBody: Json = Json("[{\"endpoint\" : \"spotify/profile\",\n" +
        "        \"recordId\" : \"f277374c-df57-410c-aa07-d448eb9354da\",\n" +
        "        \"data\" : {\n" +
        "            \"id\" : \"21vteht26stx2pltn3bdodrqi\",\n" +
        "            \"uri\" : \"spotify:user:21vteht26stx2pltn3bdodrqi\",\n" +
        "            \"href\" : \"https://api.spotify.com/v1/users/21vteht26stx2pltn3bdodrqi\",\n" +
        "            \"type\" : \"user\",\n" +
        "            \"email\" : \"liz_chandler@yahoo.com\",\n" +
        "            \"country\" : \"LT\",\n" +
        "            \"product\" : \"open\",\n" +
        "            \"birthdate\" : \"1998-01-18\",\n" +
        "            \"dateCreated\" : \"2018-04-09T06:18:56.208Z\",\n" +
        "            \"display_name\" : \"Liz Chandler\"}\n" +
        "        }]")

    override fun postRequest(url: String, body: String, headers: Map<String, String>, completion: (r: ResultType?) -> Unit) {
        val type = ResultType.IsSuccess
        type.statusCode = 200
        type.token = "123"
        type.resultString = null
        type.json = mockBody
        type.error = null
        completion(type)
    }

    override fun putRequest(url: String, body: String, headers: Map<String, String>, completion: (r: ResultType?) -> Unit) {
        val type = ResultType.IsSuccess
        type.statusCode = 200
        type.token = "123"
        type.resultString = null
        type.json = mockBody
        type.error = null
        completion(type)
    }

    override fun uploadRequest(url: String, body: String, headers: Map<String, String>, completion: (r: ResultType?) -> Unit) {
        val type = ResultType.IsSuccess
        type.statusCode = 200
        type.token = "123"
        type.resultString = null
        type.json = mockBody
        type.error = null
        completion(type)
    }

    override fun deleteRequest(url: String, parameters: List<Pair<String, Any?>>?, headers: Map<String, String>?, completion: (r: ResultType?) -> Unit) {
        val type = ResultType.IsSuccess
        type.statusCode = 200
        type.token = "123"
        type.resultString = null
        type.json = mockBody
        type.error = null
        completion(type)
    }

    override fun getRequest(url: String, parameters: List<Pair<String, Any?>>?, headers: Map<String, String>?, completion: (r: ResultType?) -> Unit) {
        val type = ResultType.IsSuccess
        type.statusCode = 200
        type.token = "123"
        type.resultString = null
        type.json = mockBody
        type.error = null
        completion(type)
    }

    override fun getRequestString(url: String, parameters: List<Pair<String, Any?>>?, headers: Map<String, String>, completion: (r: ResultType?) -> Unit) {

        val type = ResultType.IsSuccess
        type.statusCode = 200
        type.token = "123"
        type.resultString = "Public Key"
        type.json = null
        type.error = null
        completion(type)
    }

    @Test
    fun testGetRequest() {

        fun completion(r: ResultType?) {

            when (r) {

                ResultType.IsSuccess -> assert(r.statusCode == 200)
                ResultType.HasFailed -> assert(false)
            }
        }

        getRequest("", null, mapOf("x-auth-token" to "123"), { r -> completion(r) })
    }

    @Test
    fun testPostRequest() {

        fun completion(r: ResultType?) {

            when (r) {

                ResultType.IsSuccess -> assert(r.statusCode == 200)
                ResultType.HasFailed -> assert(false)
            }
        }

        postRequest("", "", mapOf("x-auth-token" to "123"), { r -> completion(r) })
    }

    @Test
    fun testDeleteRequest() {

        fun completion(r: ResultType?) {

            when (r) {

                ResultType.IsSuccess -> assert(r.statusCode == 200)
                ResultType.HasFailed -> assert(false)
            }
        }

        deleteRequest("", null, mapOf("x-auth-token" to "123"), { r -> completion(r) })
    }
}
