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
import java.io.Serializable

data class HATDataDebitPermissionsObject(
    // MARK: - Variables

    // The created date of the permission
    val dateCreated: String = "",
    // The purpose of the permission
    val purpose: String?,
    // The start date of the permission
    val start: String?,
    // The end date of the permission
    val end: String?,
    // A flag indicating if the permissions will auto cancel when the debit will end
    val cancelAtPeriodEnd: Boolean = false,
    // The terms and conditions URL for the permissions
    val termsUrl: String?,
    // The period duration
    val period: Number = 0,
    // Is the permission active
    val active: Boolean = false,
    // Is the permission accepted
    val accepted: Boolean = false,
    // It is possible for a permission to have an inner bundle object
    val bundle: DataOfferRequiredDataDefinitionObjectV2
) : Serializable
