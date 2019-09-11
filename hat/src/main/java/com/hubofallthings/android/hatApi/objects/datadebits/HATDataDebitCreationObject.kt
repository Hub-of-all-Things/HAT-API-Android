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

package com.hubofallthings.android.hatApi.objects.datadebits

import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferRequiredDataDefinitionObjectV2

data class HATDataDebitCreationObject(
    var dataDebitKey: String = "",
    var purpose: String = "",
    var start: String = "",
    var period: Double = 0.0,
    var termsUrl: String = "",
    var cancelAtPeriodEnd: Boolean = false,
    var requestClientName: String = "",
    var requestClientUrl: String = "",
    var requestClientLogoUrl: String = "",
    var requestApplicationId: String? = "",
    var requestDescription: String? = "",
    var conditions: DataOfferRequiredDataDefinitionObjectV2?,
    var bundle: DataOfferRequiredDataDefinitionObjectV2
)
