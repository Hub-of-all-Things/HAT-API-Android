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

package com.hubofallthings.android.hatApi.objects

// The operator JSON format
data class Operator(
    var `operator`: String = "between",
    var lower: Int = 0,
    var upper: Int = 0
)
// The filter JSON format
data class Filter(
    var field: String = "",
    var `operator`: Operator = Operator(),
    var transformation: Transformation? = null
)
// The filter JSON format
data class Transformation(
    var transformation: String? = null,
    var part: String? = null
)
// The combinator JSON format
data class BodyRequest(
    var endpoint: String = "rumpel/locations/android",
    var filters: ArrayList<Filter> = arrayListOf(Filter())
)
