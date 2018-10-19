package com.hubofallthings.android.hatApi

import android.util.Log
import com.hubofallthings.android.hatApi.managers.HATParserManager
import com.hubofallthings.android.hatApi.managers.toKotlinObject
import com.hubofallthings.android.hatApi.objects.feed.HATFeedObject
import org.junit.Assert.*
import org.junit.Test

class HATTestFeedParser{
    private val feedJson = """
    [
    {
        "source": "facebook",
        "date": {
            "iso": "2018-11-15T11:00:00.000Z",
            "unix": 1542279600
        },
        "types": [
            "event"
        ],
        "title": {
            "text": "test",
            "subtitle": "15 November 11:00 - 14:00 GMT",
            "action": "event"
        },
        "content": {
            "text": "test facebook source"
        }
    },
    {
        "source": "google",
        "date": {
            "iso": "2018-11-15T10:00:00.000Z",
            "unix": 1542276000
        },
        "types": [
            "event"
        ],
        "title": {
            "text": "Daily Scrum",
            "subtitle": "15 November 10:00 - 10:30 GMT",
            "action": "event"
        },
        "content": {
            "text": "test google source"
        }
    }
    ]"""
    private val feedJsonObj = """
    {
        "source": "facebook",
        "date": {
            "iso": "2018-11-15T11:00:00.000Z",
            "unix": 1542279600
        },
        "types": [
            "event"
        ],
        "title": {
            "text": "test",
            "subtitle": "15 November 11:00 - 14:00 GMT",
            "action": "event"
        },
        "content": {
            "text": "test facebook source"
        }
    }
    """
    @Test
    fun testParseFeedList(){
        val feedObj = HATParserManager().jsonToObjectList(feedJson,HATFeedObject::class.java)
        val expectedSource = "facebook"
        assertEquals(expectedSource,feedObj[0].source)
        val expectedDate = "2018-11-15T10:00:00.000Z"
        assertEquals(expectedDate,feedObj[1].date.iso)
        val expectedContentText = "test google source"
        assertEquals(expectedContentText,feedObj[1].content?.text)
        val expectedLocation = null
        assertEquals(expectedLocation,feedObj[0].location)
    }
    @Test
    fun testParseFeed(){
        val feedObj = feedJsonObj.toKotlinObject<HATFeedObject?>()
        val expectedSource = "facebook"
        assertEquals(expectedSource,feedObj?.source)
        val expectedSubtitle = "15 November 11:00 - 14:00 GMT"
        assertEquals(expectedSubtitle,feedObj?.title?.subtitle)
        val expectedContentText = "test facebook source"
        assertEquals(expectedContentText,feedObj?.content?.text)
        val expectedLocation = null
        assertEquals(expectedLocation,feedObj?.location)
    }
    @Test
    fun testParseEmptyFeed(){
        val feedObj = HATParserManager().jsonToObjectList("",HATFeedObject::class.java)
        val expectedSource = listOf<HATFeedObject>()
        assertEquals(expectedSource,feedObj)
    }
}