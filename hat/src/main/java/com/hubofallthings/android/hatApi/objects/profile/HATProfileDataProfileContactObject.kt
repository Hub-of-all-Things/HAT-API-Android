package com.hubofallthings.android.hatApi.objects.profile
import java.io.Serializable

data class HATProfileDataProfileContactObject(

        // MARK: - Variables
        /// The user's mobile number
        var mobile: String = "",
                /// The user's landline
        var landline: String = "",
                /// The user's primary email
        var primaryEmail: String = "",
                /// The user's alternative email
        var alternativeEmail: String = ""
): Serializable