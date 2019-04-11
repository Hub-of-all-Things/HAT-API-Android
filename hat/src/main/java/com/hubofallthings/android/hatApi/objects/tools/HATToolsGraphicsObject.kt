package com.hubofallthings.android.hatApi.objects.tools

import com.hubofallthings.android.hatApi.objects.extrernalapps.HATExternalAppsIllustrationObject
import java.io.Serializable

data class HATToolsGraphicsObject(
        var logo: HATExternalAppsIllustrationObject,
        var screenshots:  Array<HATExternalAppsIllustrationObject>
) : Serializable