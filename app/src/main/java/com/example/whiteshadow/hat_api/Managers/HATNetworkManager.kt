package com.example.whiteshadow.hat_api.Managers

import com.github.kittinunf.fuel.android.core.Json

interface NetworkLayer {

    fun asyncRequest()
    fun asyncRequestString()
}

enum class ResultType(statusCode: Int?, error: Error?, json: Json?, resultString: String?, token: String?){

    isSuccess(statusCode = null, error = null, json = null, resultString = null, token = null),
    hasFailed(statusCode = null, error = null, json = null, resultString = null, token = null)
}

class HATNetworkManager: NetworkLayer {

    var resultType: ResultType? = null

    override fun asyncRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun asyncRequestString() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getQueryStringParameter(url: String?, param: String): String? {


        return null
    }
}