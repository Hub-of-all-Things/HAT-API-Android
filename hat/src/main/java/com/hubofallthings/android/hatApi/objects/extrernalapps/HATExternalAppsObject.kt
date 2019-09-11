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

data class HATExternalAppsObject(
    val id: String = "",
    val kind: HATExternalAppsKindObject? = null,
    val info: HATExternalAppsInfoObject? = null,
    var developer: HATExternalAppsInfoDeveloperObject,
    val permissions: HATExternalAppsPermissionsObject? = null,
    val setup: HATExternalAppsSetupObject? = null,
    val status: HATExternalAppsStatusObject? = null
) : Serializable
