package com.hubofallthings.android.hatApi.objects.extrernalapps

import java.io.Serializable

data class HATExternalAppsObject(
        val id: String = "",

        val kind: HATExternalAppsKindObject? = null,

        val info: HATExternalAppsInfoObject? = null,

        var developer: HATExternalAppsInfoDeveloperObject,

        val permissions: HATExternalAppsPermissionsObject? = null,

        val setup: HATExternalAppsSetupObject? = null,

        val status: HATExternalAppsStatusObject? = null
) : Serializable