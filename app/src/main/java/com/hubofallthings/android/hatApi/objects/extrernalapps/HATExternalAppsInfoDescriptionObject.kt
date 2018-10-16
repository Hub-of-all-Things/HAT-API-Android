package com.hubofallthings.hatappandroid.`object`.externalapps

import java.io.Serializable

data class HATExternalAppsInfoDescriptionObject(
        // MARK: - Variables

        /// The description text as simple String
        val text : String? = null,

        /// The description text markdown formatted
        val markdown : String? = null,

        /// The description text html formatte
        val html : String? = null
): Serializable