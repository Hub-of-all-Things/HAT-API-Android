package com.example.whiteshadow.hat_api

import android.net.UrlQuerySanitizer
import com.example.whiteshadow.hat_api.Managers.HATNetworkManager
import com.example.whiteshadow.hat_api.Managers.NetworkLayer
import com.example.whiteshadow.hat_api.Managers.ResultType
import org.junit.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class HATNetworkManagerTest: NetworkLayer {

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

        var expectedToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxLXRqWlA3cHdkaFVSaXdDMHE2YjltS1ZTdDNHVXVCZmtYRDNLNHFlXC9xaFhKNXlHblFpNG9IU0FMU0hnbCtQZzI0eDlqYXZ5SWJ5N3pGUTA0WHVBSFJTZHUrTWc4MWRMbTBXaWNSM0E9PSIsInJlc291cmNlIjoibGV5bXl0ZWwuaHViYXQubmV0IiwiYWNjZXNzU2NvcGUiOiJvd25lciIsImlzcyI6ImxleW15dGVsLmh1YmF0Lm5ldCIsImV4cCI6MTUyNzA5OTc2OSwiaWF0IjoxNTI0NTA3NzY5LCJqdGkiOiI0NjllODZlOTQxNjQ3MjM4NTUxOTVmM2JmZTEyYTFmMDViNmI2MDdjODhiZjIzZDhiZGYxYzIzNzJhNTU3YTMxYzM3ODViODZlYmVjYTMwMmJlZjlmZjYyYzEzZGU1MTllZTVlN2JmNDRlY2Y1YzEyODcyNmYzODhjOGJjMWYyM2FmOWM5MDI0YjliZjlmYThjMDEzZGIwMTYyM2UzZWUxNDU3OWEwMTRiN2UxZTNhYTYxNGQ2MTAyYmU5Mzk5N2NmZTIyYzZiYzg5OWRjYzZmMDZiZmNmY2I5Njc5OTgyNWM4ZGM3YTZkNDY1ZmQxYzhkNzRhNmQ3MTAxMzAxMWUwIn0.hsFTG6NQAVi8XEag0o4nITEW0x3KNgK3Ij8JotaKl2y_Jxgzu0aLZy1lc7nZ-MYQFUkK0Q164QJDdseAg_euw1Y3IfaNbnfvoU4pk8A9887YDAVfaX2BK-If7wbYdY-h8ZLwyUHlJuBBCJarCS-yLsbkb0069DRKxW6M9TIxl4u2mtdRUAoPwzqQFqbl7S0vFAbwZQleB0zlnkrKFbqlI7WbSUMTDLK7MnOjcm0LssuT-BZsQ1TFZqWV6xdgy-rGevWnJcHZ32IFsruSBk2eTey4z47e-g-WSVFFdnzs-PqvkmBzR_Jt8e1ZL-jpesrr1pjujqEH3mTKQVk-sjdu6g"

        val url = "hatapp://hatapphost?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxLXRqWlA3cHdkaFVSaXdDMHE2YjltS1ZTdDNHVXVCZmtYRDNLNHFlXC9xaFhKNXlHblFpNG9IU0FMU0hnbCtQZzI0eDlqYXZ5SWJ5N3pGUTA0WHVBSFJTZHUrTWc4MWRMbTBXaWNSM0E9PSIsInJlc291cmNlIjoibGV5bXl0ZWwuaHViYXQubmV0IiwiYWNjZXNzU2NvcGUiOiJvd25lciIsImlzcyI6ImxleW15dGVsLmh1YmF0Lm5ldCIsImV4cCI6MTUyNzA5OTc2OSwiaWF0IjoxNTI0NTA3NzY5LCJqdGkiOiI0NjllODZlOTQxNjQ3MjM4NTUxOTVmM2JmZTEyYTFmMDViNmI2MDdjODhiZjIzZDhiZGYxYzIzNzJhNTU3YTMxYzM3ODViODZlYmVjYTMwMmJlZjlmZjYyYzEzZGU1MTllZTVlN2JmNDRlY2Y1YzEyODcyNmYzODhjOGJjMWYyM2FmOWM5MDI0YjliZjlmYThjMDEzZGIwMTYyM2UzZWUxNDU3OWEwMTRiN2UxZTNhYTYxNGQ2MTAyYmU5Mzk5N2NmZTIyYzZiYzg5OWRjYzZmMDZiZmNmY2I5Njc5OTgyNWM4ZGM3YTZkNDY1ZmQxYzhkNzRhNmQ3MTAxMzAxMWUwIn0.hsFTG6NQAVi8XEag0o4nITEW0x3KNgK3Ij8JotaKl2y_Jxgzu0aLZy1lc7nZ-MYQFUkK0Q164QJDdseAg_euw1Y3IfaNbnfvoU4pk8A9887YDAVfaX2BK-If7wbYdY-h8ZLwyUHlJuBBCJarCS-yLsbkb0069DRKxW6M9TIxl4u2mtdRUAoPwzqQFqbl7S0vFAbwZQleB0zlnkrKFbqlI7WbSUMTDLK7MnOjcm0LssuT-BZsQ1TFZqWV6xdgy-rGevWnJcHZ32IFsruSBk2eTey4z47e-g-WSVFFdnzs-PqvkmBzR_Jt8e1ZL-jpesrr1pjujqEH3mTKQVk-sjdu6g"

        val token= HATNetworkManager().getQueryStringParameter(url, "token")

        assertTrue(token.equals(expectedToken))
    }
}