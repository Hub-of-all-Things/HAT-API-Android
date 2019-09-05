/*
 * *
 *  * Copyright (C) 2018-2019 DataSwift Ltd
 *  *
 *  * SPDX-License-Identifier: MPL2
 *  *
 *  * This file is part of the Hub of All Things project (HAT).
 *  *
 *  * This Source Code Form is subject to the terms of the Mozilla Public
 *  * License, v. 2.0. If a copy of the MPL was not distributed with this
 *  * file, You can obtain one at http://mozilla.org/MPL/2.0/
 *  
 */

package com.hubofallthings.android.hatApi.objects.dataoffers

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class DataOfferObject(
        // MARK: - Variables

        /// The data offer ID

        @JsonProperty("id") var dataOfferID: String = "",
        /// The title of the offer
        var title: String = "",
        /// The short description of the offer
        var shortDescription: String = "",
        /// The long description of the offer
        var longDescription: String = "",
        /// The image URL of the offer
        var illustrationURL: String = "",
        /// The merchant code of the offer
        var merchantCode: String = "",
        /// the date created as unix time stamp
        @JsonProperty("created") var dateCreated: String = "",
                /// the start date of the offer as unix time stamp
        @JsonProperty("starts") var offerStarts: String = "",
                /// the expire date of the offer as unix time stamp
        @JsonProperty("expires") var offerExpires: String = "",
                /// the duration that the offer collects data for as unix time stamp
        var collectsDataFor: Int = -1,
                /// the minimum users required for the offer to activate
        var requiredMinUsers: Int = -1,
                /// the max users of the offer
        var requiredMaxUsers: Int = -1,
                /// the number of the offer claimed the offer so far
        var usersClaimedOffer: Int = -1,
                /// the data definition object of the offer
        var requiredDataDefinition: DataOfferRequiredDataDefinitionObjectV2?,

                /// the data conditions object of the offer
        var dataConditions: DataOfferRequiredDataDefinitionObjectV2?,

                /// the data requirements object of the offer
        var dataRequirements: DataOfferRequiredDataDefinitionObjectV2?,

                /// the rewards of the offer
        var reward: DataOfferRewardsObject,

                /// The owner of the offer
        var owner: DataOfferOwnerObject,
                /// The claim object of the offer
        var claim: DataOfferClaimObject?,

        @JsonProperty("illustrationUrl") var imageUrl : String?,

        /// Is the offer requiring pii
        var isPIIRequested: Boolean = false
): Serializable