package com.hubofallthings.hatappandroid.`object`.externalapps

import java.io.Serializable

data class  HATExternalAppsKindObject(
    // MARK: - Variables
    /// The iTunes url of the app
    val url: String = "",
    /// The iOS url to launch the app
    val iosUrl: String? = null,
    /// The kind of the app
    val kind: String = ""
): Serializable