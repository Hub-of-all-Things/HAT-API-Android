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

data class HATExternalAppsStatusObject(
    // MARK: - Variables

    // The compatibility of this app
    val compatibility: String = "",
    // The endpoing this app will be reading or writing on HAT
    val recentDataCheckEndpoint: String = "",
    val staticDataPreviewEndpoint: String? = null,
    val dataPreviewEndpoint: String? = null,
    // The kind of the app
    val kind: String = "",
    val statusUrl: String? = null,
    val expectedStatus: Int? = null,
    var versionReleaseDate: String = ""
) : Serializable
