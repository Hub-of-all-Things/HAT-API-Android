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

data class HATProfileDataProfileEmergencyContactObject(
        // MARK: - Variables
        // The type of relationship with the user
        var relationship: String = "",
        // The last name of the emergency contact
        var lastName: String = "",
        // The mobile number of the emergency contact
        var mobile: String = "",
        // The first name of the emergency contact
        var firstName: String = ""
) : Serializable