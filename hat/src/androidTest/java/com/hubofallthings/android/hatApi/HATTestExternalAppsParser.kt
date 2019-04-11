package com.hubofallthings.android.hatApi

import com.hubofallthings.android.hatApi.managers.HATParserManager
import com.hubofallthings.android.hatApi.managers.toKotlinObject
import com.hubofallthings.android.hatApi.objects.extrernalapps.HATApplicationObject
import org.junit.Assert
import org.junit.Test

class HATTestExternalAppsParser {
    val externalApp = """{
    "application": {
        "id": "spotify",
        "kind": {
            "url": "https://spotify.hubofallthings.com",
            "kind": "DataPlug"
        },
        "info": {
            "version": "1.0.0",
            "published": true,
            "name": "Spotify",
            "headline": "Spotify in the HAT",
            "description": {
                "text": "\nSpotify Data Plug is an “API-to-API” app that fetches data from your Spotify account and pushes it into your HAT microserver.\n\nIt allows you to have real-time, on demand subject access to your Spotify music listens and other Spotify data in your HAT\n          ",
                "markdown": "\nSpotify Data Plug is an “API-to-API” app that fetches data from your Spotify account and pushes it into your HAT microserver.\n\nIt allows you to have real-time, on demand subject access to your Spotify music listens and other Spotify data in your HAT\n            ",
                "html": "\n <p>Spotify Data Plug is an “API-to-API” app that fetches data from your Spotify account and pushes it into your HAT microserver.</p>\n\n<p>It allows you to have real-time, on demand subject access to your Spotify music listens and other Spotify data in your HAT</p>\n            "
            },
            "termsUrl": "https://hatdex.org/website-terms-service",
            "dataUsePurpose": "Spotify Data Plug does not retrieve any data from your HAT",
            "supportContact": "contact@hatdex.org",
            "rating": {
                "score": "A*A*A*"
            },
            "dataPreview": [
                {
                    "source": "spotify",
                    "date": {
                        "iso": "2018-10-10T10:22:10.799Z",
                        "unix": 1539166930
                    },
                    "types": [
                        "tweet"
                    ],
                    "title": {
                        "text": "You listened",
                        "action": "03:44"
                    },
                    "content": {
                        "text": "Summer Guest,\nÁsgeir,\nIn the Silence (The Deluxe Edition)",
                        "media": [
                            {
                                "thumbnail": "https://i.scdn.co/image/4d2a323b9d5f763797ceb106e493d04f3abd54bd",
                                "url": "https://i.scdn.co/image/4d2a323b9d5f763797ceb106e493d04f3abd54bd"
                            }
                        ]
                    }
                },
                {
                    "source": "spotify",
                    "date": {
                        "iso": "2018-10-10T10:22:10.799Z",
                        "unix": 1539166930
                    },
                    "types": [
                        "tweet"
                    ],
                    "title": {
                        "text": "You listened",
                        "action": "06:53"
                    },
                    "content": {
                        "text": "The Rockafeller Skank - Short Edit,\nFatboy Slim,\nYou've Come a Long Way Baby (10th Anniversary Edition)",
                        "media": [
                            {
                                "thumbnail": "",
                                "url": ""
                            }
                        ]
                    }
                }
            ],
            "graphics": {
                "banner": {
                    "normal": ""
                },
                "logo": {
                    "normal": "https://spotify.hubofallthings.com/assets/logo.png"
                },
                "screenshots": []
            }
        },
        "developer": {
            "id": "hatdex",
            "name": "HAT Data Exchange Ltd",
            "url": "https://hatdex.org",
            "country": "United Kingdom"
        },
        "permissions": {
            "rolesGranted": [
                {
                    "role": "namespacewrite",
                    "detail": "spotify"
                }
            ]
        },
        "setup": {
            "url": "https://spotify.hubofallthings.com/authenticate/hat",
            "kind": "External"
        },
        "status": {
            "compatibility": "1.0.0",
            "statusUrl": "https://spotify.hubofallthings.com/api/status",
            "expectedStatus": 200,
            "dataPreviewEndpoint": "she/feed/spotify",
            "recentDataCheckEndpoint": "spotify/feed",
            "versionReleaseDate": "2018-07-23T12:00:00.000Z",
            "kind": "External"
        }
    },
    "setup": true,
    "enabled": true,
    "active": false,
    "needsUpdating": false,
    "mostRecentData": "2018-04-19T05:20:09.052Z"
}"""

    @Test
    fun testParseApp() {
        val externalAppObj = externalApp.toKotlinObject<HATApplicationObject?>()
        val expectedAppID = "spotify"
        Assert.assertEquals(expectedAppID, externalAppObj?.application?.id)
        val expecteddataPreviewEndpoint = "she/feed/spotify"
        Assert.assertEquals(expecteddataPreviewEndpoint, externalAppObj?.application?.status?.dataPreviewEndpoint)
        val expectedKind = "DataPlug"
        Assert.assertEquals(expectedKind, externalAppObj?.application?.kind?.kind)
        val expectedEnabled = true
        Assert.assertEquals(expectedEnabled, externalAppObj?.enabled)
    }

    @Test
    fun testParseAppsList() {
        val externalAppObj = HATParserManager().jsonToObjectList(externalApp, HATApplicationObject::class.java)
        val expectedAppID = "spotify"
        Assert.assertEquals(expectedAppID, externalAppObj.get(0).application?.id)
        val expectedDataPreviewEndpoint = "she/feed/spotify"
        Assert.assertEquals(expectedDataPreviewEndpoint, externalAppObj.get(0).application?.status?.dataPreviewEndpoint)
        val expectedKind = "DataPlug"
        Assert.assertEquals(expectedKind, externalAppObj.get(0).application?.kind?.kind)
        val expectedEnabled = true
        Assert.assertEquals(expectedEnabled, externalAppObj.get(0).enabled)
    }
}