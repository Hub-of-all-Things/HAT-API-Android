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

import com.hubofallthings.android.hatApi.managers.HATNetworkManager

data class LoginLookupSuccess(
    val hatName: String,
    val hatCluster: String
)

data class LoginLookupError(
    val message: String,
    val cause: String
)

class HATLoginService {

    // MARK: - Validate Data

    /**
    Lookup for HAT address with the HAT

    - parameter baseUrl: The base url to validate with the HAT f.e https://hatters.hubat.net
    - parameter username: The username to lookup with the HAT. Optional
    - parameter email: The email to lookup with the HAT. Optional
    - parameter sandbox: If the application is sandbox or not
    - parameter successfulCallBack: A function to call if everything is ok and returns hatName and hatCluster
    - parameter failCallBack: A function to call if fail
     */
    fun hatLookup(baseUrl: String, username: String? = null, email: String? = null, sandbox: Boolean? = false, successfulCallBack: (LoginLookupSuccess) -> Unit, failCallBack: (LoginLookupError) -> Unit) {
        val url = "$baseUrl/api/hat/lookup"
        val parameters = mutableListOf("sandbox" to sandbox.toString())

        if (!username.isNullOrEmpty()) {
            parameters.add("name" to username)
        }

        if (!email.isNullOrEmpty()) {
            parameters.add("email" to email)
        }

        HATNetworkManager().getRequest(url, parameters, null) {
            if (it?.statusCode == 200) {
                if (it.json == null) {
                    failCallBack(LoginLookupError("Lookup failed", "Neither email nor HAT name provided"))
                }

                it.json?.let { json ->
                    val hatCluster: String? = json.obj()["hatCluster"].toString()
                    val hatName: String? = json.obj()["hatName"].toString()

                    if (!hatName.isNullOrEmpty() && !hatCluster.isNullOrEmpty()) {
                        successfulCallBack(LoginLookupSuccess(hatName, hatCluster))
                    } else {
                        failCallBack(LoginLookupError("Lookup failed", "Neither email nor HAT name provided"))
                    }
                }
            } else {
                it?.json?.let { json ->
                    val message: String? = json.obj()["message"].toString()
                    val cause: String? = json.obj()["cause"].toString()

                    failCallBack(LoginLookupError(message ?: "Lookup failed", cause ?: "Unexpected error"))
                }

                if (it?.json == null) {
                    failCallBack(LoginLookupError("Lookup failed", "Neither email nor HAT name provided"))
                }
            }
        }
    }
}
