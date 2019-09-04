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

import java.io.Serializable

data class HATDataDebitObject (
    // MARK: - Variables
    /// The data debit key
        val dataDebitKey: String = "",
    /// The date created of the data debit
        val dateCreated: String = "",
    /// The permissions of the data debit
        val permissions: Array<HATDataDebitPermissionsObject>,
    /// The client name of the data debit
        val requestClientName: String = "",
    /// The client url of the data debit
        val requestClientUrl: String = "",
    /// The client logo URL of the data debit
        val requestClientLogoUrl: String = "",
    /// The description of the data debit
        val requestDescription: String?,
    /// The id of the application, if the data debit is an application
        val requestApplicationId: String?,
    /// Is data debit active?
        val active: Boolean = false,
    /// The start date of the data debit
        val start: String?,
    /// The end date of the data debit
        val end: String?,
    /// Are permissions still active
        val permissionsActive: HATDataDebitPermissionsObject?,
    /// The last permission set
        val permissionsLatest: HATDataDebitPermissionsObject
    ) : Serializable