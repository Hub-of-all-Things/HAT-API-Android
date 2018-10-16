package com.hubofallthings.hatappandroid.`object`.externalapps

import java.io.Serializable

data class HATExternalAppsSetupOnboardingObject(
        val illustration: HATExternalAppsIllustrationObject? = null,
        val title: String = "",
        val description: String = ""
): Serializable