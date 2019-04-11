package com.hubofallthings.android.hatApi.objects.fileUploadObject

data class FileUploadObjectPermissions(
    // The user id that uploaded the file
    var userID: String = "",
    // Is the content readable
    var contentReadable: String = ""
)