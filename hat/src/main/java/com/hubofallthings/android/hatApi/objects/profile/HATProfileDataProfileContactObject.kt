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

package com.hubofallthings.android.hatApi.objects.profile
import java.io.Serializable

data class HATProfileDataProfileContactObject(
    // MARK: - Variables

    // The user's mobile number
    var mobile: String = "",
    // The user's landline
    var landline: String = "",
    // The user's primary email
    var primaryEmail: String = "",
    // The user's alternative email
    var alternativeEmail: String = ""
) : Serializable
