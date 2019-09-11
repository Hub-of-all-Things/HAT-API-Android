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

data class HATProfileDataObject(
    // The website object of user's profile
    var about: HATProfileDataProfileAboutObject? = null,
    // The nickname object of user's profile
    var photo: HATProfileDataProfilePhotoObject? = null,
    // The primary email address object of user's profile
    var online: HATProfileDataProfileOnlineObject? = null,
    // The youtube object of user's profile
    var address: HATProfileDataProfileAddressObject? = null,
    // The global addres object of user's profile
    var contact: HATProfileDataProfileContactObject? = null,
    // The youtube object of user's profile
    var personal: HATProfileDataProfilePersonalObject? = null,
    // The global addres object of user's profile
    var emergencyContact: HATProfileDataProfileEmergencyContactObject? = null,
    // The date the profile was created in unix time stamp
    var dateCreated: Int? = null,
    // The date the profile was created in ISO format
    var dateCreatedLocal: String? = null,
    // Is profile shared
    var shared: Boolean = false
) : Serializable
