package com.hubofallthings.android.hatApi.objects.dataoffers

import java.io.Serializable

data class DataOfferRequiredDataDefinitionObjectV2(
        // MARK: - Variables

        // The name of the definition
        val name: String? = null,
        val bundle: List<Map<String, DataOfferRequiredDataDefinitionBundleKeyV2>>

        // The requirements of the offer
): Serializable
