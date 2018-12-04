package com.hubofallthings.android.hatApi.objects.dataoffers

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class DataOfferOwnerObject(
        // MARK: - Variables
        /// The offer issuer ID
        @JsonProperty("id") var issuerID: String = "",
        /// The email of the issuer
        var email: String = "",
        /// The nickname of the issuer
        @JsonProperty("nick") var nickName: String = "",
        /// The first name of the issuer
        var firstName: String = "",
        /// The last name of the issuer
        var lastName: String = ""
): Serializable
