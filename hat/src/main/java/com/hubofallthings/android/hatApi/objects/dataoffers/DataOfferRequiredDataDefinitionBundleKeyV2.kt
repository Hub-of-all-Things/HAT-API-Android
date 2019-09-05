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

package com.hubofallthings.android.hatApi.objects.dataoffers

import java.io.Serializable

data class DataOfferRequiredDataDefinitionBundleKeyV2(
        // MARK: - Variables

        // The endpoints of the definition
        val endpoints: Array<DataOfferRequiredDataDefinitionBundleKeyEndpointsV2>? = null,
        // The ordering filter of the defition
        val orderBy: String? = null,
        // The order, ascending of descending
        val ordering: String? = null,
       // The limit of the filter
        val limit: Int? = null
): Serializable