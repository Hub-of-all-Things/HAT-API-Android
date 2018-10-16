package com.hubofallthings.hatappandroid.`object`.externalapps

import com.hubofallthings.hatappandroid.`object`.FeedItem
import java.io.Serializable

data class  HATExternalAppsInfoObject(

    // MARK: - Variables

    /// The current version of the app
    val version: String = "",

    /// A bool indicating if the app has been published or not
    val published: Boolean = false,

    /// The name of the app
    val name: String = "",

    /// The headline text to describe the app
    val headline: String = "",

    /// The support email address
    var supportContact: String = "",

    /// The rating of the app
    val rating: HATExternalAppsInfoRatingObject? = null,

    /// The description text of the app
    val description: HATExternalAppsInfoDescriptionObject? = null,

    /// The data preview of the app
    val dataPreview: Array<FeedItem>?,

    /// The graphics, images, needed for this app (logo, banner and screenshots)
    val graphics: HATExternalAppsInfoGraphicsObject,

    val termsUrl : String? = null,

    val dataUsePurpose : String? = null
): Serializable