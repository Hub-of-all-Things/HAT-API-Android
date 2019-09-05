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

package com.hubofallthings.android.hatApi.objects.fileUploadObject

data class FileUploadObject(
    // MARK: - Variables

    // The file ID of the uploaded file
    var fileId: String = "",
    // The name of the uploaded file
    var name: String = "",
    // The source of the uploaded file
    var source: String = "",
    // The tags of the uploaded file
    var tags: Array<String>,
    // The image of the uploaded file
    var image: String?,
    // The title of the uploaded file
    var title: String = "",
    // The description of the uploaded file
    var fileDescription: String = "",
    // The created date of the uploaded file
    var dateCreated: String?,
    // The last updated date of the uploaded file
    var lastUpdated: String?,
    // The current status of the uploaded file
    var status: FileUploadObjectStatus,
    // The image url of the uploaded file
    var contentUrl: String = "",
    // Is the uploaded file public
    var contentPublic : Boolean= false,
    // The permissions of the uploaded file
    var permissions: Array<FileUploadObjectPermissions>
)
data class Fields(
    val fileId: String = "fileId",
    val name: String = "name",
    val source: String = "source",
    val tags: String = "tags",
    val title: String = "title",
    val description: String = "description",
    val dateCreated: String = "dateCreated",
    val lastUpdated: String = "lastUpdated",
    val contentUrl: String = "contentUrl",
    val contentPublic: String = "contentPublic",
    val status: String = "status",
    val permissions: String = "permissions",
    val unixTimeStamp: String = "unixTimeStamp"
)