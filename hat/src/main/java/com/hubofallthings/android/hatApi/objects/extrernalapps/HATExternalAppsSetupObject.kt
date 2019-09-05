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

data class HATExternalAppsSetupObject (
        // MARK: - Variables

        // The url needed to launch the app from another app
        val iosUrl: String? = null,
        // The url needed to open in safari
        val url: String? = null,
        // The kind of app this is
        val kind: String = "",

        val preferences: String? = null,

        val onboarding: Array<HATExternalAppsSetupOnboardingObject>? = null
) : Serializable