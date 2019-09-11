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

import com.hubofallthings.android.hatApi.managers.HATParserManager
import com.hubofallthings.android.hatApi.managers.toKotlinObject
import com.hubofallthings.android.hatApi.objects.tools.HATToolsObject
import org.junit.Assert
import org.junit.Test

class HATTestToolsParser {
    private val toolsJson = """{
        "id": "test-tool",
        "info": {
            "version": "1.0.0",
            "versionReleaseDate": "2018-01-01T12:00:00.000Z",
            "name": "Sentiment Tracker",
            "headline": "Sentiment in your words",
            "description": {
                "text": "Sentiment Tracker analyses your texts on Facebook, Twitter and Notables to work out how negative or positive your postings are."
            },
            "termsUrl": "https://hatdex.org/terms-of-service-hat-owner-agreement",
            "supportContact": "contact@hatdex.org",
            "graphics": {
                "banner": {
                    "normal": ""
                },
                "logo": {
                    "normal": "https://github.com/Hub-of-all-Things/exchange-assets/blob/master/Sentiments/logo.png?raw=true"
                },
                "screenshots": [
                    {
                        "normal": "https://github.com/Hub-of-all-Things/exchange-assets/blob/master/Sentiments/Screenshot1.jpg?raw=true"
                    },
                    {
                        "normal": "https://github.com/Hub-of-all-Things/exchange-assets/blob/master/Sentiments/Screenshot2.jpg?raw=true"
                    }
                ]
            },
            "dataPreviewEndpoint": "/she/feed/she/sentiments"
        },
        "developer": {
            "id": "hatdex",
            "name": "HAT Data Exchange Ltd",
            "url": "https://hatdex.org",
            "country": "United Kingdom"
        },
        "trigger": {
            "triggerType": "individual"
        },
        "dataBundle": {
            "name": "data-feed-counter",
            "bundle": {
                "she/insights/emotions": {
                    "endpoints": [
                        {
                            "endpoint": "she/insights/emotions",
                            "mapping": {
                                "message": "text",
                                "timestamp": "timestamp"
                            }
                        }
                    ],
                    "orderBy": "timestamp",
                    "ordering": "descending",
                    "limit": 20
                },
                "notables/feed": {
                    "endpoints": [
                        {
                            "endpoint": "rumpel/notablesv1",
                            "mapping": {
                                "message": "message",
                                "timestamp": "created_time"
                            }
                        }
                    ],
                    "orderBy": "created_time",
                    "ordering": "descending",
                    "limit": 20
                },
                "she/insights/emotions/negative": {
                    "endpoints": [
                        {
                            "endpoint": "she/insights/emotions/negative",
                            "mapping": {
                                "message": "text",
                                "timestamp": "timestamp"
                            }
                        }
                    ],
                    "orderBy": "timestamp",
                    "ordering": "descending",
                    "limit": 20
                },
                "twitter/tweets": {
                    "endpoints": [
                        {
                            "endpoint": "twitter/tweets",
                            "mapping": {
                                "message": "text",
                                "timestamp": "lastUpdated"
                            }
                        }
                    ],
                    "orderBy": "lastUpdated",
                    "ordering": "descending",
                    "limit": 20
                },
                "facebook/feed": {
                    "endpoints": [
                        {
                            "endpoint": "facebook/feed",
                            "mapping": {
                                "message": "message",
                                "timestamp": "created_time"
                            }
                        }
                    ],
                    "orderBy": "created_time",
                    "ordering": "descending",
                    "limit": 20
                },
                "she/insights/emotions/neutral": {
                    "endpoints": [
                        {
                            "endpoint": "she/insights/emotions/neutral",
                            "mapping": {
                                "message": "text",
                                "timestamp": "timestamp"
                            }
                        }
                    ],
                    "orderBy": "timestamp",
                    "ordering": "descending",
                    "limit": 20
                },
                "she/insights/emotions/positive": {
                    "endpoints": [
                        {
                            "endpoint": "she/insights/emotions/positive",
                            "mapping": {
                                "message": "text",
                                "timestamp": "timestamp"
                            }
                        }
                    ],
                    "orderBy": "timestamp",
                    "ordering": "descending",
                    "limit": 20
                }
            }
        },
        "status": {
            "available": true,
            "enabled": true,
            "lastExecution": "2018-10-18T08:57:52.914Z"
        }
    }
"""

    @Test
    fun testParseTool() {
        val toolObj = toolsJson.toKotlinObject<HATToolsObject?>()
        val expectedAppID = "test-tool"
        Assert.assertEquals(expectedAppID, toolObj?.id)
        val expecteddataPreviewEndpoint = "/she/feed/she/sentiments"
        Assert.assertEquals(expecteddataPreviewEndpoint, toolObj?.info?.dataPreviewEndpoint)
        val expectedExecution = "2018-10-18T08:57:52.914Z"
        Assert.assertEquals(expectedExecution, toolObj?.status?.lastExecution)
        val expectedEnabled = true
        Assert.assertEquals(expectedEnabled, toolObj?.status?.enabled)
    }

    @Test
    fun testParseToolList() {
        val toolObj = HATParserManager().jsonToObjectList(toolsJson, HATToolsObject::class.java)
        val expectedAppID = "test-tool"
        Assert.assertEquals(expectedAppID, toolObj[0].id)
        val expecteddataPreviewEndpoint = "/she/feed/she/sentiments"
        Assert.assertEquals(expecteddataPreviewEndpoint, toolObj[0].info.dataPreviewEndpoint)
        val expectedExecution = "2018-10-18T08:57:52.914Z"
        Assert.assertEquals(expectedExecution, toolObj[0].status.lastExecution)
        val expectedEnabled = true
        Assert.assertEquals(expectedEnabled, toolObj[0].status.enabled)
    }
}
