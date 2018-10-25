package com.hubofallthings.hatappandroid.`object`.profile

import java.io.Serializable

data class HATProfileDataProfilePersonalObject(
        // MARK: - Variables

        /// The user's title
        var title: String = "",
                /// The user's gender
        var gender: String = "",
                /// The user's age group
        var ageGroup: String = "",
                /// The user's middle name
        var middleName: String = "",
                /// The user's preferred name
        var preferredName: String = "",
                /// The user's last name
        var lastName: String = "",
                /// The user's nick name
        var nickName: String = "",
                /// The user's first name
        var firstName: String = "",
                /// The user's birth date
        var birthDate: String = ""
): Serializable