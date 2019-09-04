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

package com.hubofallthings.android.hatApi.objects.tools

import com.hubofallthings.android.hatApi.objects.extrernalapps.HATExternalAppsInfoDescriptionObject
import com.hubofallthings.android.hatApi.objects.feed.HATFeedObject
import java.io.Serializable

data class HATToolsInfoObject(
        var version: String = "",
        var versionReleaseDate: String = "",
        var name: String = "",
        var headline: String = "",
        var description: HATExternalAppsInfoDescriptionObject,
        var termsUrl: String = "",
        var supportContact: String = "",
        var dataPreview: Array<HATFeedObject>?,
        var graphics: HATToolsGraphicsObject,
        var dataPreviewEndpoint: String = ""
) : Serializable