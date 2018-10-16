package com.hubofallthings.hatappandroid.`object`.dataoffers

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

data class DataOfferRequiredDataDefinitionBundleFiltersV2(
        // MARK: - Variables

        // the field to filter
        val field: String = "",
        // The transformation to be done on the field
        val transformation: Map<String, String>? = null,
        /// The operator of the filter
        @JsonIgnore
        var operator: Any?
): Serializable