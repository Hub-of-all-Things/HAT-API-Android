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
import com.hubofallthings.android.hatApi.objects.datadebits.HATDataDebitObject
import org.junit.Assert
import org.junit.Test

class HATTestDataDebitParser {
    val dataDebitJson = """{
        "dataDebitKey": "2534fdfw-fasfas-fas-fas-fas-fas-f",
        "dateCreated": "2018-01-02T15:22:42+0000",
        "permissions": [
            {
                "dateCreated": "2018-01-02T15:22:42+0000",
                "purpose": "This Data Debit is in a legacy format, and the HAT App is unable to display all the information associated with it fully. This may include a logo, title and full description",
                "start": "2018-01-02T15:23:02.000Z",
                "period": 86400000,
                "cancelAtPeriodEnd": true,
                "termsUrl": "",
                "bundle": {
                    "name": "fsdjfi434i534ofj4ipj24i4j-f24r2fd",
                    "bundle": {
                        "iphone/locations": {
                            "endpoints": [
                                {
                                    "endpoint": "iphone/locations",
                                    "mapping": {
                                        "accuracy": "accuracy",
                                        "latitude": "latitude",
                                        "longitude": "longitude",
                                        "timestamp": "timestamp",
                                        "lastUpdated": "lastUpdated",
                                        "locations.accuracy": "locations.accuracy",
                                        "locations.latitude": "locations.latitude",
                                        "locations.longitude": "locations.longitude",
                                        "locations.timestamp": "locations.timestamp"
                                    },
                                    "filters": []
                                }
                            ]
                        },
                        "rumpel/locations/ios": {
                            "endpoints": [
                                {
                                    "endpoint": "rumpel/locations/ios",
                                    "mapping": {
                                        "speed": "speed",
                                        "course": "course",
                                        "altitude": "altitude",
                                        "latitude": "latitude",
                                        "longitude": "longitude",
                                        "dateSynced": "dateSynced",
                                        "dateCreated": "dateCreated",
                                        "dateCreatedLocal": "dateCreatedLocal",
                                        "verticalAccuracy": "verticalAccuracy",
                                        "horizontalAccuracy": "horizontalAccuracy"
                                    },
                                    "filters": []
                                }
                            ]
                        }
                    }
                },
                "accepted": false,
                "active": false,
                "end": "2018-01-03T15:23:02.000Z"
            }
        ],
        "requestClientName": "Data Exchange",
        "requestClientUrl": "https://dex.hubofallthings.com/",
        "requestClientLogoUrl": "https://dex.hubofallthings.com/assets//images/dex.png",
        "active": false,
        "accepted": false,
        "start": "2018-01-02T15:23:02.000Z",
        "end": "2018-01-03T15:23:02.000Z",
        "permissionsActive": null,
        "permissionsLatest": {
            "dateCreated": "2018-01-02T15:22:42+0000",
            "purpose": "This Data Debit is in a legacy format, and the HAT App is unable to display all the information associated with it fully. This may include a logo, title and full description",
            "start": "2018-01-02T15:23:02.000Z",
            "period": 86400000,
            "cancelAtPeriodEnd": true,
            "termsUrl": "",
            "bundle": {
                "name": "ac54tegt34t34g34",
                "bundle": {
                    "iphone/locations": {
                        "endpoints": [
                            {
                                "endpoint": "iphone/locations",
                                "mapping": {
                                    "accuracy": "accuracy",
                                    "latitude": "latitude",
                                    "longitude": "longitude",
                                    "timestamp": "timestamp",
                                    "lastUpdated": "lastUpdated",
                                    "locations.accuracy": "locations.accuracy",
                                    "locations.latitude": "locations.latitude",
                                    "locations.longitude": "locations.longitude",
                                    "locations.timestamp": "locations.timestamp"
                                },
                                "filters": []
                            }
                        ]
                    },
                    "rumpel/locations/ios": {
                        "endpoints": [
                            {
                                "endpoint": "rumpel/locations/ios",
                                "mapping": {
                                    "speed": "speed",
                                    "course": "course",
                                    "altitude": "altitude",
                                    "latitude": "latitude",
                                    "longitude": "longitude",
                                    "dateSynced": "dateSynced",
                                    "dateCreated": "dateCreated",
                                    "dateCreatedLocal": "dateCreatedLocal",
                                    "verticalAccuracy": "verticalAccuracy",
                                    "horizontalAccuracy": "horizontalAccuracy"
                                },
                                "filters": []
                            }
                        ]
                    }
                }
            },
            "accepted": false,
            "active": false,
            "end": "2018-01-03T15:23:02.000Z"
        }
        }
    """

    @Test
    fun testParseDataDebitList() {
        val dataDebitObj = HATParserManager().jsonToObjectList(dataDebitJson, HATDataDebitObject::class.java)
        val expectedDebitKey = "2534fdfw-fasfas-fas-fas-fas-fas-f"
        Assert.assertEquals(expectedDebitKey, dataDebitObj.get(0).dataDebitKey)
        val expectedDataDebitPermissionDateCreated = "2018-01-02T15:22:42+0000"
        Assert.assertEquals(expectedDataDebitPermissionDateCreated, dataDebitObj.get(0).permissions[0].dateCreated)
        val expectedEnd = "2018-01-03T15:23:02.000Z"
        Assert.assertEquals(expectedEnd, dataDebitObj.get(0).end)
        val expectedActive = false
        Assert.assertEquals(expectedActive, dataDebitObj.get(0).active)
    }

    @Test
    fun testParseDataDebit() {
        val dataDebitObj = dataDebitJson.toKotlinObject<HATDataDebitObject?>()
        val expectedDebitKey = "2534fdfw-fasfas-fas-fas-fas-fas-f"
        Assert.assertEquals(expectedDebitKey, dataDebitObj?.dataDebitKey)
        val expectedDataDebitPermissionDateCreated = "2018-01-02T15:22:42+0000"
        Assert.assertEquals(expectedDataDebitPermissionDateCreated, dataDebitObj!!.permissions[0].dateCreated)
        val expectedEnd = "2018-01-03T15:23:02.000Z"
        Assert.assertEquals(expectedEnd, dataDebitObj.end)
        val expectedActive = false
        Assert.assertEquals(expectedActive, dataDebitObj.active)
    }
}
