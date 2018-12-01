package com.hubofallthings.android.hatApi.objects.dataoffers

import java.io.Serializable

data class DataOfferRequiredDataDefinitionBundleKeyEndpointsV2(
        // MARK: - Variables

        // The endpoint of the definition object
        val endpoint: String = "",
        // The mapping of the definition object
        val mapping: Map<String, String>? = null,
        // The filters of the definition object
        val filters: Array<DataOfferRequiredDataDefinitionBundleFiltersV2>? = null,
        // The links of the definition object
        val links: Array<DataOfferRequiredDataDefinitionBundleKeyEndpointsV2>? = null
): Serializable