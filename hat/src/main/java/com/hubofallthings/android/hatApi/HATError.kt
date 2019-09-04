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

package com.hubofallthings.android.hatApi

enum class ExceptionType {

    Unauthorised,
    BadRequest,
    Forbidden,
    GeneralError,
    TimeOut,
    NoInternetConnection
}

class HATError: Exception() {

    var errorMessage: String? = null
    var errorCode: Int? = null
    var error: Exception? = null
    var exceptionType: ExceptionType? = null

    fun throwError(message: String?, errorCode: Int?, errorException: Exception?): HATError {

        this.errorMessage = message
        this.errorCode = errorCode
        this.error = errorException

        if (errorCode == 401) {
            this.exceptionType = ExceptionType.Unauthorised
        } else if (errorCode == 403) {

            this.exceptionType = ExceptionType.Forbidden
        } else if (errorCode == 400) {

            this.exceptionType = ExceptionType.BadRequest
        } else {

            this.exceptionType = ExceptionType.GeneralError
        }

        return this
    }
}