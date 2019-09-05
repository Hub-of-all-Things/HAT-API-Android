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

data class HATProfileDataProfilePersonalObject(
        // MARK: - Variables

        // The user's title
        var title: String = "",
        // The user's gender
        var gender: String = "",
        // The user's age group
        var ageGroup: String = "",
        // The user's middle name
        var middleName: String = "",
        // The user's preferred name
        var preferredName: String = "",
        // The user's last name
        var lastName: String = "",
        // The user's nick name
        var nickName: String = "",
        // The user's first name
        var firstName: String = "",
        // The user's birth date
        var birthDate: String = ""
) : Serializable