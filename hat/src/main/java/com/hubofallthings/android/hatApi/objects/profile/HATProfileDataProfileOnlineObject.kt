package com.hubofallthings.android.hatApi.objects.profile
import java.io.Serializable

data class HATProfileDataProfileOnlineObject(
        // MARK: - Variables

        // The user's blog address
        var blog: String = "",
        // The user's google address
        var google: String = "",
        // The user's twitter address
        var twitter: String = "",
        // The user's website address
        var website: String = "",
        // The user's youtube address
        var youtube: String = "",
        // The user's facebook address
        var facebook: String = "",
        // The user's linkedin address
        var linkedin: String = ""
) : Serializable