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

data class HATProfileDataProfileOnlineObject(
        // MARK: - Variables

        // The user's blog address
        var blog: String = "",
        // The user's google address
        var google: String = "",
        // The user's twitter address
        var twitter: String = "",
        // The user's website address
        var website: String = "",
        // The user's youtube address
        var youtube: String = "",
        // The user's facebook address
        var facebook: String = "",
        // The user's linkedin address
        var linkedin: String = ""
) : Serializable