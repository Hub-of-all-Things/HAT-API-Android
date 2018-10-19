package com.hubofallthings.android.hatApi.objects.extrernalapps

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