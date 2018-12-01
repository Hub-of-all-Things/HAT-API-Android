package com.hubofallthings.android.hatApi.objects.extrernalapps

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferRequiredDataDefinitionObjectV2
import java.io.Serializable

data class HATExternalAppsDataRequiredObject(
        // MARK: - Variables

        val rolling: Boolean = false,
        // The bundle info definition for this app
        @JsonSetter(nulls = Nulls.AS_EMPTY)
         val bundle: DataOfferRequiredDataDefinitionObjectV2? = null,
        val startDate: String? = null,
        val endDate: String? = null
): Serializable