package com.hubofallthings.hatappandroid.`object`.externalapps

import java.io.Serializable

data class HATExternalAppsInfoDeveloperObject(
    var id: String = "",
    var name: String = "",
    var url: String = "",
    var country: String = ""
): Serializable