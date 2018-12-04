package com.hubofallthings.android.hatApi.objects.dataoffers

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class DataOfferClaimObject(
        // MARK: - Variables

        /// The data offer claim status
        @JsonProperty("status") var claimStatus: String = "",
        /// The data offer claim confirmed state
        @JsonProperty("confirmed") var claimConfirmed: Boolean = false,
        /// The data offer claim unix time stamp
        @JsonProperty("dateCreated") var claimDateStamp: String = "",
        var dataDebitID: String = ""
): Serializable