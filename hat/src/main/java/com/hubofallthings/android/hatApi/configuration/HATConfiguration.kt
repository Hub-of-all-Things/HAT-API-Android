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

package com.hubofallthings.android.hatApi.configuration

class VerifiedDomains{

    fun verifiedHATDomains(): Array<String> {

        return arrayOf(".hubofallthings.net", ".hubat.net", "hat.direct", "savy.io")
    }
}

class ContentType {

    val json: String = "application/json"
    val plain: String = "text/plain"
}

class RequestHeaders {

    val xAuthToken: String = "x-auth-token"
    val tokenParamName: String = "token"
}

class TokenParameters {

    val userDomain: String = "iss"
    val application: String = "application"
}