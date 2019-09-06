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

data class HATExternalAppsIllustrationObject(
    // MARK: - Variables

    // The small dimensions image
    val small: String? = null,
    // The normal dimensions image
    val normal: String = "",
    // The large dimensions image
    val large: String? = null,
    // The extra large dimensions image
    val xlarge: String? = null
) : Serializable
