package com.hubofallthings.hatappandroid.`object`.tools

import com.hubofallthings.hatappandroid.`object`.FeedItem
import com.hubofallthings.hatappandroid.`object`.externalapps.HATExternalAppsInfoDescriptionObject
import java.io.Serializable

data class HATToolsInfoObject(
    var version: String = "",
    var versionReleaseDate: String = "",
    var name: String = "",
    var headline: String = "",
    var description: HATExternalAppsInfoDescriptionObject,
    var termsUrl: String = "",
    var supportContact: String = "",
    var dataPreview: Array<FeedItem>?,
    var graphics: HATToolsGraphicsObject,
    var dataPreviewEndpoint: String = ""
): Serializable