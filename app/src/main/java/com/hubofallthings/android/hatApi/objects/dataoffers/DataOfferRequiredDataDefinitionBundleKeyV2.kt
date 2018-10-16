package com.hubofallthings.hatappandroid.`object`.dataoffers

import java.io.Serializable

data class DataOfferRequiredDataDefinitionBundleKeyV2(
        // MARK: - Variables

        // The endpoints of the definition
        val endpoints: Array<DataOfferRequiredDataDefinitionBundleKeyEndpointsV2>? = null,
        // The ordering filter of the defition
        val orderBy: String? = null,
        // The order, ascending of descending
        val ordering: String? = null,
       // The limit of the filter
        val limit: Int? = null
): Serializable