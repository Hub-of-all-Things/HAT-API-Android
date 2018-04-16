package com.example.whiteshadow.hat_api

import com.example.whiteshadow.hat_api.Managers.HATNetworkManager
import com.example.whiteshadow.hat_api.Managers.NetworkLayer
import com.example.whiteshadow.hat_api.Managers.ResultType
import org.junit.Test

class HATNetworkManagerMock: NetworkLayer {

    val body = listOf(mapOf(
        "endpoint" to "spotify/profile",
        "recordId" to "f277374c-df57-410c-aa07-d448eb9354da",
        "data" to mapOf(
            "id" to "21vteht26stx2pltn3bdodrqi",
            "uri" to "spotify:user:21vteht26stx2pltn3bdodrqi",
            "href" to "https://api.spotify.com/v1/users/21vteht26stx2pltn3bdodrqi",
            "type" to "user",
            "email" to "liz_chandler@yahoo.com",
            "country" to "LT",
            "product" to "open",
            "birthdate" to "1998-01-18",
            "dateCreated" to "2018-04-09T06:18:56.208Z",
            "display_name" to "Liz Chandler"
        )
    ))

    override fun getRequest(url: String, parameters: List<Pair<String, Any?>>?, headers: List<Pair<String, String>>, completion: (r: ResultType?) -> Unit) {

        val type = ResultType.IsSuccess
        type.statusCode = 200
        type.token = "123"
        type.resultString = null
        type.json = body
        type.error = null
        completion(type)
    }

    override fun getRequestString(url: String, parameters: List<Pair<String, Any?>>?, headers: List<Pair<String, String>>, completion: (r: ResultType?) -> Unit) {

        val type = ResultType.IsSuccess
        type.statusCode = 200
        type.token = "123"
        type.resultString = "Public Key"
        type.json = null
        type.error = null
        completion(type)
    }


    @Test
    fun testGetRequest() {

        fun completion(r: ResultType?): Unit {

            when (r) {

                ResultType.IsSuccess -> assert(r.statusCode == 200)
                ResultType.HasFailed -> assert(false)
            }

        }

        getRequest("", null, listOf("x-auth-token" to "123"), { r -> completion(r) })
    }

    @Test
    fun testGetQueryParameter() {

        val url = "mariostsekis.hubat.net?token=213"

        val token= HATNetworkManager().getQueryStringParameter(url, "token")

        assert(token == "213")
    }
}