package com.hubofallthings.android.hatApi.objects.extrernalapps

import java.io.Serializable

data class HATExternalAppsSetupOnboardingObject(
        val illustration: HATExternalAppsIllustrationObject? = null,
        val title: String = "",
        val description: String = ""
): Serializable