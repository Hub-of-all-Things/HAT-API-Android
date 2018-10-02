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
        } else if  (errorCode == 400) {

            this.exceptionType = ExceptionType.BadRequest
        } else {

            this.exceptionType = ExceptionType.GeneralError
        }

        return this
    }
}