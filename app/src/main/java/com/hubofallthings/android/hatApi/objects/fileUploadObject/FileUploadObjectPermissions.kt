package com.hubofallthings.hatappandroid.`object`.fileuploadobject

data class FileUploadObjectPermissions(
    /// The user id that uploaded the file
    var userID: String = "",
        /// Is the content readable
    var contentReadable: String = ""
)