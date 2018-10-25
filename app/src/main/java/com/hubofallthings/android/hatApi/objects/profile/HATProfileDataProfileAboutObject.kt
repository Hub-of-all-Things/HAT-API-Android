package com.hubofallthings.hatappandroid.`object`.profile

import java.io.Serializable

data class HATProfileDataProfileAboutObject(
        // MARK: - Variables

        /// Body part of about
        var body: String? = "",
                /// Main part of about
        var title: String? = ""
): Serializable