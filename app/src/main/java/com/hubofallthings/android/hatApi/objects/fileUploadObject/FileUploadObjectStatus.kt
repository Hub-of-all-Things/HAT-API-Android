package com.hubofallthings.hatappandroid.`object`.fileuploadobject

data class FileUploadObjectStatus(
    // MARK: - Variables
    /// The status of the uploaded file
    var status: String = "",
        /// The size of the uploaded file
    var size: Int?
)