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
import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.managers.HATNetworkManager
import com.hubofallthings.android.hatApi.objects.log.LogModel

class HATLogService {
    fun postLogToHAT(userDomain: String, userToken: String, logModel: LogModel, successfulCallBack: (String, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        val url = "https://$userDomain/api/v2.6/report-frontend-action"
        val mapper = jacksonObjectMapper()
        val logJson = mapper.writeValueAsString(logModel)
        val headers = mapOf("Content-Type" to "application/json", "x-auth-token" to userToken)

        HATNetworkManager().postRequest(url, logJson, headers) {
            if (it?.statusCode == 200) {
                successfulCallBack("result ok", "")
            } else {
                val e = HATError()
                e.errorMessage = it?.resultString
                e.errorCode = it?.statusCode
                failCallBack(e)
            }
        }
    }
}
