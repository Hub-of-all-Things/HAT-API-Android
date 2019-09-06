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

package com.hubofallthings.android.hatApi.managers

class HATFormatterManager {

    fun fromBase64URLToBase64(stringToConvert: String): String {

        var convertedString = stringToConvert
        if (convertedString.count() % 4 == 2) {

            convertedString += "=="
        } else if (convertedString.count() % 4 == 3) {

            convertedString += "="
        }

        convertedString = convertedString.replace("-", "+", true)
        convertedString = convertedString.replace("_", "/", true)

        return convertedString
    }
}
