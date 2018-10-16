package com.hubofallthings.hatappandroid.`object`.externalapps

import java.io.Serializable

data class HATExternalAppsInfoGraphicsObject(
        // MARK: - Variables

        // The banner logo
         val banner: HATExternalAppsIllustrationObject? = null,
         /// The app logo
         val logo: HATExternalAppsIllustrationObject? = null,
        // The screenshots used in the preview
         val screenshots: Array<HATExternalAppsIllustrationObject>? = null
): Serializable