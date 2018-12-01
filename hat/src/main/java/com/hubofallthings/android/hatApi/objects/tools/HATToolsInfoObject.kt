package com.hubofallthings.android.hatApi.objects.tools

import com.hubofallthings.android.hatApi.objects.extrernalapps.HATExternalAppsInfoDescriptionObject
import com.hubofallthings.android.hatApi.objects.feed.HATFeedObject
import java.io.Serializable

data class HATToolsInfoObject(
        var version: String = "",
        var versionReleaseDate: String = "",
        var name: String = "",
        var headline: String = "",
        var description: HATExternalAppsInfoDescriptionObject,
        var termsUrl: String = "",
        var supportContact: String = "",
        var dataPreview: Array<HATFeedObject>?,
        var graphics: HATToolsGraphicsObject,
        var dataPreviewEndpoint: String = ""
): Serializable