package com.hubofallthings.android.hatApi.objects.tools
import com.hubofallthings.hatappandroid.`object`.externalapps.HATExternalAppsInfoDeveloperObject
import java.io.Serializable

data class HATToolsObject(
        var id: String = "",
        var info: HATToolsInfoObject,
        var developer: HATExternalAppsInfoDeveloperObject,
        var status: HATToolsStatusObject,
        var dataBundle: HATToolsDataBundleObject,
        var trigger: HATToolsTriggerObject
): Serializable