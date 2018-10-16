package com.hubofallthings.hatappandroid.`object`.externalapps

import java.io.Serializable

data class HATExternalAppsSetupObject (
        // MARK: - Variables

        /// The url needed to launch the app from another app
        val iosUrl: String? = null,
        /// The url needed to open in safari
        val url: String? = null,
        /// The kind of app this is
        val kind: String = "",

        val preferences: String? = null,

        val onboarding: Array<HATExternalAppsSetupOnboardingObject>? = null
): Serializable