package com.hubofallthings.android.hatApi.managers

import android.net.UrlQuerySanitizer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.hubofallthings.android.hatApi.HATError

interface NetworkLayer {

    fun getRequest(url: String, parameters: List<Pair<String, Any?>>? = null, headers: Map<String, String>, completion: (r: ResultType?) -> Unit)
    fun postRequest(url: String, body: String, headers: Map<String, String>, completion: (r: ResultType?) -> Unit)

    fun getRequestString(url: String, parameters: List<Pair<String, Any?>>? = null, headers: Map<String, String>, completion: (r: ResultType?) -> Unit)
}

enum class ResultType(var statusCode: Int?, var error: Error?, var json: com.github.kittinunf.fuel.android.core.Json?, var resultString: String?, var token: String?){

    IsSuccess(null, null, null, null, null),
    HasFailed(null, null, null,null, null);
}

class HATNetworkManager: NetworkLayer {

    var resultType: ResultType? = null

    override fun getRequest(url: String , parameters: List<Pair<String, Any?>>?, headers: Map<String, String>, completion: (r: ResultType?) -> Unit) {

        FuelManager.instance.baseHeaders = headers
        Fuel.get(url, parameters).responseJson{ _, response, result ->

            //do something with response
            when (result) {

                is Result.Failure -> {

                    val ex = result.getException()
                    val error = Error(ex)

                    val test = ResultType.HasFailed
                    test.statusCode = response.statusCode
                    test.error = error
                    test.json = null
                    test.resultString = null
                    test.token = null

                    resultType = test
                    completion(resultType)
                }
                is Result.Success -> {

                    val token = response.headers["X-Auth-Token"]?.last()
                    val test2 = result.component1()

                    val test = ResultType.IsSuccess
                    test.statusCode = response.statusCode
                    test.error = null
                    test.json = test2
                    test.resultString = null
                    test.token = token


                    resultType = test
                    completion(resultType)
                }
            }
        }
    }

    override fun getRequestString(url: String , parameters: List<Pair<String, Any?>>?, headers: Map<String, String>, completion: (r: ResultType?) -> Unit) {

        url.httpGet(parameters).responseString{_, response, result ->

            //do something with response
            when (result) {

                is Result.Failure -> {

                    val ex = result.getException()
                    val error = Error(ex)
                    val test = ResultType.HasFailed
                    test.statusCode = response.statusCode
                    test.error = error
                    test.json = null
                    test.resultString = null
                    test.token = null

                    resultType = test
                    completion(resultType)
                }
                is Result.Success -> {
                    val token = response.headers["X-Auth-Token"]?.last()
                    val test2 = result.component1()
                    val test = ResultType.IsSuccess
                    test.statusCode = response.statusCode
                    test.error = null
                    test.json = null
                    test.resultString = test2
                    test.token = token


                    resultType = test
                    completion(resultType)
                }
            }
        }
    }

    override fun postRequest(url: String, body : String, headers: Map<String, String>, completion: (r: ResultType?) -> Unit) {
        val timeout = 35000 // 35 seconds.
        val timeoutRead = 35000 // 35 seconds.
        FuelManager.instance.baseHeaders = headers

        Fuel.post(url).body(body).timeout(timeout).timeoutRead(timeoutRead).responseJson { _, response, result ->
            when (result){
                is Result.Failure -> {
                    val ex = result.getException()
                    val error = Error(ex)
                    val test = ResultType.HasFailed
                    test.statusCode = response.statusCode
                    test.error = error
                    test.json = null
                    test.resultString = null
                    test.token = null

                    resultType = test
                    completion(resultType)
                }
                is Result.Success -> {
                    val token = response.headers["X-Auth-Token"]?.last()
                    val test2 = result.component1()
                    val test = ResultType.IsSuccess
                    test.statusCode = response.statusCode
                    test.error = null
                    test.json = test2
                    test.resultString = null
                    test.token = token

                    resultType = test
                    completion(resultType)
                }
            }
        }    }
    fun getQueryStringParameter(url: String?, param: String): String? {

        if (!url.isNullOrEmpty()) {

            val sanitizer = UrlQuerySanitizer()
            sanitizer.allowUnregisteredParamaters = true
            sanitizer.parseUrl(url)
            return sanitizer.getValue(param)
        }

        return null
    }
}