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

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferRequiredDataDefinitionObjectV2
import java.io.Serializable

data class HATExternalAppsPermissionsObject(
    // MARK: - Variables

    // Any roles granted with this app
    val rolesGranted: Array<HATExternalAppsPermissionsRolesGrantedObject>? = null,
    // The bundle info required for this app
    val dataRequired: HATExternalAppsDataRequiredObject? = null,
    // The bundle info required for this app
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    val dataRetrieved: DataOfferRequiredDataDefinitionObjectV2? = null
) : Serializable
