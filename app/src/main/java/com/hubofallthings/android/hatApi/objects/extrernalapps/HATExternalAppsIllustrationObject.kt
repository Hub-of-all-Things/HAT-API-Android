package com.hubofallthings.android.hatApi.objects.extrernalapps

import java.io.Serializable

data class HATExternalAppsIllustrationObject(

        // MARK: - Variables

        // The small dimensions image
        val small: String? = null,
        // The normal dimensions image
        val normal: String = "",
        // The large dimensions image
        val large: String? = null,
        // The extra large dimensions image
        val xlarge: String? = null
): Serializable