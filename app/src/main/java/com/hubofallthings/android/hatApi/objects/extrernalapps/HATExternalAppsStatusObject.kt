package com.hubofallthings.android.hatApi.objects.extrernalapps

import java.io.Serializable

data class HATExternalAppsStatusObject(
        // MARK: - Variables

        /// The compatibility of this app
        val compatibility: String = "",
        /// The endpoing this app will be reading or writing on HAT
        val recentDataCheckEndpoint: String = "",
        /// The kind of the app
        val kind: String = "",
        val statusUrl: String? = null,
        val expectedStatus: Int? = null,
        var versionReleaseDate: String = "",

        val dataPreviewEndpoint: String? = null
): Serializable