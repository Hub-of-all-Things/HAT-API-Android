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

import com.hubofallthings.android.hatApi.objects.feed.HATFeedDate
import com.hubofallthings.android.hatApi.objects.feed.HATFeedObject
import com.hubofallthings.android.hatApi.services.FeedService
import org.junit.Assert.assertEquals
import org.junit.Test

class HATTestFeedService : FeedService {
    private val mockFeedObj = HATFeedObject(HATFeedDate("2018-11-15T11:00:00.000Z", 12523634643), source = "facebook", types = arrayOf("event"))
    private val tokenReturn = "2592fsdfsdf348j0572v89jhf9dsyguisd"

    override fun getFeed(userDomain: String, userToken: String, parameters: List<Pair<String, Any>>?, hatSuffix: String, completion: (List<HATFeedObject>?, String?) -> Unit, failCallBack: (HATError) -> Unit) {
        if (userToken.isEmpty()) {
            val e = HATError()
            e.errorMessage = "empty token"
            e.errorCode = 403
            failCallBack(e)
        } else {
            completion(listOf(mockFeedObj), tokenReturn)
        }
    }

    @Test
    fun testFeedServiceCompletion() {
        fun completion(list: List<HATFeedObject>?, newToken: String?) {
            val expectedUnixDate = mockFeedObj.date.unix
            val expectedToken = tokenReturn
            assertEquals(expectedUnixDate, list!![0].date.unix)
            assertEquals(expectedToken, newToken)
        }

        fun failcallback() {}
        getFeed("test.hubat.net", "254tijrgiosdgjsdsdfkisd9dsyguisd", null, "", { list: List<HATFeedObject>?, s: String? -> completion(list, s) }, { _ -> failcallback() })
    }

    @Test
    fun testFeedServiceFailCallback() {
        fun completion() {}
        fun failcallback(error: HATError) {
            assertEquals(403, error.errorCode)
            assertEquals("empty token", error.errorMessage)
        }
        getFeed("test.hubat.net", "", null, "", { _: List<HATFeedObject>?, _: String? -> completion() }, { error -> failcallback(error) })
    }
}
