package com.hubofallthings.hatappandroid.`object`.profile

import java.io.Serializable

data class HATProfileDataProfileEmergencyContactObject(
        // MARK: - Variables
        /// The type of relationship with the user
        var relationship: String = "",
                /// The last name of the emergency contact
        var lastName: String = "",
                /// The mobile number of the emergency contact
        var mobile: String = "",
                /// The first name of the emergency contact
        var firstName: String = ""
): Serializable