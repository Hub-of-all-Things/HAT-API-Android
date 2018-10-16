package com.hubofallthings.android.hatApi.objects.tools

import com.hubofallthings.hatappandroid.`object`.externalapps.HATExternalAppsIllustrationObject
import java.io.Serializable

data class HATToolsGraphicsObject(
    var logo: HATExternalAppsIllustrationObject,
    var screenshots :  Array<HATExternalAppsIllustrationObject>
): Serializable