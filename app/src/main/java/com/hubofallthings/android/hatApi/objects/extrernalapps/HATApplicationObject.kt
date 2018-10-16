package com.hubofallthings.hatappandroid.`object`.externalapps

import java.io.Serializable

data class HATApplicationObject(
        val application : HATExternalAppsObject? = null,
        val setup : Boolean = false,
        val active : Boolean = false,
        val enabled : Boolean = false,
        val needsUpdating : Boolean? = null,
        val mostRecentData : String? = null
): Serializable