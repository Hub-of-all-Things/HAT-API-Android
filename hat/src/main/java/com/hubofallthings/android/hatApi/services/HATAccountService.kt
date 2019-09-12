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

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelManager
import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.managers.ResultType
import com.hubofallthings.android.hatApi.objects.BodyRequest
import com.hubofallthings.android.hatApi.objects.Transformation

class HATAccountService {

    // MARK: - Get hat values from a table

    /**
    Gets values from a particular table in use with v2 API

    - parameter token: The user's token
    - parameter userDomain: The user's domain
    - parameter namespace: The namespace to read from
    - parameter scope: The scope to read from
    - parameter parameters: The parameters to pass to the request, e.g. startime, endtime, limit
    - parameter successCallback: A callback called when successful of type @escaping ([JSON], String?) -> Void
    - parameter failCallback: A callback called when failed of type @escaping (HATTableError) -> Void)
     */
    fun getHatTableValues(token: String, userDomain: String, namespace: String, scope: String, parameters: List<Pair<String, Any?>>?, successCallback: (Json, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url = "https://$userDomain/api/v2.6/data/$namespace/$scope"

        HATNetworkManager().getRequest(url, parameters, mapOf(
            "Content-Type" to "application/json",
            "x-auth-token" to token
        )) {
            if (it?.statusCode == 200) {
                if (it.json == null) {
                    val e = HATError()
                    e.errorMessage = "Json is null"
                    e.errorCode = it.statusCode
                    failCallBack(e)
                }

                it.json?.let { json ->
                    successCallback(json, it.token)
                }
            } else {
                val e = HATError()
                e.errorMessage = "Result type is not success"
                e.errorCode = it?.statusCode
                failCallBack(e)
            }
        }
    }

    // MARK: - Create value

    /**
    Gets values from a particular table in use with v2 API

    - parameter userToken: The user's token
    - parameter userDomain: The user's domain
    - parameter namespace: The namespace to read from
    - parameter scope: The scope to read from
    - parameter body: The body to pass to the request, e.g. { "value": "my name" }
    - parameter successCallback: A callback called when successful of type @escaping (JSON, String?) -> Void
    - parameter failCallback: A callback called when failed of type @escaping (HATTableError) -> Void)
     */
    fun createTableValue(token: String, userDomain: String, namespace: String, scope: String, body: String, successCallback: (Json, String?) -> Unit, failCallback: (HATError) -> Unit) {
        // form the url
        val url = "https://$userDomain/api/v2.6/data/$namespace/$scope"

        HATNetworkManager().postRequest(url, body, mapOf(
            "Content-Type" to "application/json",
            "x-auth-token" to token
        )) {
            when (it) {
                ResultType.IsSuccess -> {
                    if (it.json == null) {
                        val e = HATError()
                        e.errorCode = it.statusCode
                        e.errorMessage = it.error?.localizedMessage
                        failCallback(e)
                    }

                    it.json?.let {json ->
                        successCallback(json, it.token)
                    }
                }
                ResultType.HasFailed -> {
                    val e = HATError()
                    e.errorCode = it.statusCode
                    e.errorMessage = it.error?.localizedMessage
                    failCallback(e)
                }
                null -> {
                    val e = HATError()
                    e.errorCode = it?.statusCode
                    e.errorMessage = it?.error?.localizedMessage
                    failCallback(e)
                }
            }
        }
    }

    // MARK: - Reset Password

    /**
    Resets the user's password

    - parameter userDomain: The user's domain
    - parameter email: The old password the user entered
    - parameter successCallback: A function of type (String, String?) to call on success
    - parameter failCallback: A fuction of type (HATError) to call on fail
     */
    fun resetPassword(userDomain: String, email: String, successCallback: (String?) -> Unit, failCallback: (HATError) -> Unit) {
        val url = "https://$userDomain/control/v2/auth/passwordReset"

        val mapper = jacksonObjectMapper()
        val passwordResetBody = mapper.writeValueAsString(mapOf("email" to email))

        HATNetworkManager().postRequest(url, passwordResetBody, mapOf("Content-Type" to "application/json")) {
            if (it?.statusCode == 200) {
                successCallback(it.token)
            } else {
                val e = HATError()
                e.errorCode = it?.statusCode
                e.errorMessage = it?.error?.localizedMessage
                failCallback(e)
            }
        }
    }

    /**
    Creates combinator

    - parameter userDomain: The user's domain
    - parameter userToken: The user's authentication token
    - parameter combinatorName: The desired combinator name
    - parameter fieldToFilter: The field to filter with
    - parameter lowerValue: The lower value of the filter
    - parameter upperValue: The upper value of the filter
    - parameter successCallback: A function of type (Boolean, String?) to call on success
    - parameter failCallback: A fuction of type (HATError) to call on fail
     */
    fun createCombinator(userDomain: String, userToken: String, endPoint: String, combinatorName: String, fieldToFilter: String, lowerValue: Int, upperValue: Int, transformationType: String? = null, transformationPart: String? = null, successCallback: (Boolean, String?) -> Unit, failCallback: (HATError) -> Unit) {
        val url = "https://$userDomain/api/v2.6/combinator/$combinatorName"
        val bodyRequest = arrayListOf(BodyRequest())

        bodyRequest[0].endpoint = endPoint
        bodyRequest[0].filters[0].field = fieldToFilter
        bodyRequest[0].filters[0].`operator`.lower = lowerValue
        bodyRequest[0].filters[0].`operator`.upper = upperValue

        if (transformationPart != null && transformationType != null) {

            bodyRequest[0].filters[0].transformation = Transformation()
            bodyRequest[0].filters[0].transformation?.part = transformationPart
            bodyRequest[0].filters[0].transformation?.transformation = transformationType
        }
        val bodyJsonString = bodyRequestToJsonString(bodyRequest)

        HATNetworkManager().postRequest(url, bodyJsonString, mapOf("Content-Type" to "application/json", "x-auth-token" to userToken)) {
            when (it) {
                ResultType.IsSuccess -> {
                    successCallback(true, it.statusCode.toString())
                }
                ResultType.HasFailed -> {
                    val e = HATError()
                    e.errorCode = it.statusCode
                    e.errorMessage = it.error?.localizedMessage
                    failCallback(e)
                }
            }
        }
    }

    // MARK: - Delete from hat

    /**
    Deletes a record from hat using V2 API

    - parameter userDomain: The user's domain
    - parameter userToken: The user's token
    - parameter recordIds: The record id to delete
    - parameter success: A callback called when the request was successful of type @escaping (String) -> Void
    - parameter failed: A callback called when the request failed of type @escaping (HATTableError) -> Void
     */
    fun deleteHatRecord(userDomain: String, userToken: String, recordIds: Array<String>, success: (String) -> Unit, failed: (HATError) -> Unit) {
        val url: String = "https://$userDomain/api/v2.6/data"
        val headers = mapOf("x-auth-token" to userToken)
        val params = mutableListOf<Pair<String, Any?>>()
        for (i in recordIds.indices) {
            params.add("records" to recordIds[i])
        }
        HATNetworkManager().deleteRequest(url, params, headers) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    if (r.statusCode != 401 && r.statusCode != 403) {
                        triggerHatUpdate(userDomain) {}
                        success(userToken)
                    } else {
                        val error = HATError()
                        error.errorCode = r.statusCode
                        error.errorMessage = r.resultString
                        failed(error)
                    }
                }
                ResultType.HasFailed -> {
                    val error = HATError()
                    error.errorCode = r.statusCode
                    error.errorMessage = r.resultString
                    failed(error)
                }
                null -> {
                }
            }
        }
    }

    // MARK: - Trigger an update

    /**
    Triggers an update to hat servers
     */
    fun triggerHatUpdate(userDomain: String, completion: () -> Unit) {

        // define the url to connect to
        val url: String = "https://notables.hubofallthings.com/api/bulletin/tickle"

        HATNetworkManager().getRequest(
                url,
                listOf("phata" to userDomain),
                null) { r ->
            when (r) {
                ResultType.IsSuccess -> {
                    completion()
                }
                ResultType.HasFailed -> {
                    completion()
                }
                null -> {
                }
            }
        }
    }

    private fun bodyRequestToJsonString(body: ArrayList<BodyRequest>): String {
        val mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(body)
    }

    fun getCombinator(userDomain: String, userToken: String, combinatorName: String, completion: (result: ResultType?) -> Unit) {
        val url = "https://$userDomain/api/v2.6/combinator/$combinatorName"
        HATNetworkManager().getRequest(url, null, mapOf("x-auth-token" to userToken)) { r -> completion(r) }
    }
}
