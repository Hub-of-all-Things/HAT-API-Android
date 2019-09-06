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

package com.hubofallthings.android.hatApi.objects.dataoffers

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class DataOfferOwnerObject(
    // MARK: - Variables

    // The offer issuer ID
    @JsonProperty("id") var issuerID: String = "",
    // The email of the issuer
    var email: String = "",
    // The nickname of the issuer
    @JsonProperty("nick") var nickName: String = "",
    // The first name of the issuer
    var firstName: String = "",
    // The last name of the issuer
    var lastName: String = ""
) : Serializable
