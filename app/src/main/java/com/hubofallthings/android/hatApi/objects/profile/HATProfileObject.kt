package com.hubofallthings.hatappandroid.`object`.profile

import java.io.Serializable

data class HATProfileObject(
        // MARK: - Variables

        /// The endpoint the data are coming from
        var endpoint: String? = "",
        /// The record ID of the data
        var recordId: String? = "",
        /// The data of the profile
        var data: HATProfileDataObject
): Serializable