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

package com.hubofallthings.android.hatApi.objects.extrernalapps

import java.io.Serializable

data class HATExternalAppsPermissionsRolesGrantedObject(
    // MARK: - Variables

    // The permission role, read, write etc
    val role: String = "",
    // The app the role applies to
    val detail: String? = null
) : Serializable
