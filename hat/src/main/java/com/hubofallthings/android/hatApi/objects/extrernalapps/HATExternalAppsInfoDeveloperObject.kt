package com.hubofallthings.android.hatApi.objects.extrernalapps

import java.io.Serializable

data class HATExternalAppsInfoDeveloperObject(
    var id: String = "",
    var name: String = "",
    var url: String = "",
    var country: String = ""
) : Serializable