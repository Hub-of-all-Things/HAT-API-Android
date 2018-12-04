package com.hubofallthings.android.hatApi.objects.dataoffers

import java.io.Serializable

data class DataOfferRewardsCashValueObject(
        // MARK: - Variables

        /// The reward type of the offer
        var rewardType: String = "",
                /// The value of the reward
        var value: Int = 0,
                /// The currency of the reward
        var currency: String = ""
): Serializable