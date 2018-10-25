package com.hubofallthings.android.hatApi.objects.profile
import java.io.Serializable

data class HATProfileDataProfileAddressObject(
        // MARK: - Variables

        /// The user's city
         var city: String = "",
                /// The user's county
         var county: String = "",
                /// The user's country
         var country: String = ""
): Serializable