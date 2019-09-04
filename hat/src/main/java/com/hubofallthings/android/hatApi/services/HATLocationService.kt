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

import com.hubofallthings.android.hatApi.HATError
import com.hubofallthings.android.hatApi.objects.locations.HATLocationsObject

class HATLocationService {
    // MARK: - Get Locations

    /**
    Gets locations from HAT

    - parameter userDomain: The user's domain
    - parameter userToken: The user's token
    - parameter fromDate: The date to request locations from
    - parameter toDate: The date to request locations to
    - parameter successCallback: A (List<HATLocationsObject>, String?) -> Unit function executed on success
    - parameter errorCallback: A (JSONParsingError) -> Unit function executed on failure
     */
    fun getLocations(userDomain: String, userToken: String, fromDate: Long, toDate: Long, successCallback: (List<HATLocationsObject>?, String?) -> Unit, errorCallback: (HATError) -> Unit) {

    }
}