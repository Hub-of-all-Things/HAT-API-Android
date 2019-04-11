package com.hubofallthings.android.hatApi.objects.profile
import java.io.Serializable

data class HATProfileDataObject(
        // The website object of user's profile
        var about: HATProfileDataProfileAboutObject? = null,
        // The nickname object of user's profile
        var photo: HATProfileDataProfilePhotoObject? = null,
        // The primary email address object of user's profile
        var online: HATProfileDataProfileOnlineObject? = null,
        // The youtube object of user's profile
        var address: HATProfileDataProfileAddressObject? = null,
        // The global addres object of user's profile
        var contact: HATProfileDataProfileContactObject? = null,
        // The youtube object of user's profile
        var personal: HATProfileDataProfilePersonalObject? = null,
        // The global addres object of user's profile
        var emergencyContact: HATProfileDataProfileEmergencyContactObject? = null,
        // The date the profile was created in unix time stamp
        var dateCreated: Int? = null,
        // The date the profile was created in ISO format
        var dateCreatedLocal: String? = null,
        // Is profile shared
        var shared: Boolean = false
) : Serializable