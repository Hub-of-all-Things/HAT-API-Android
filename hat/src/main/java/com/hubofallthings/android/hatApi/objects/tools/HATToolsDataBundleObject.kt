package com.hubofallthings.android.hatApi.objects.tools

import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferRequiredDataDefinitionBundleKeyV2
import java.io.Serializable

data class HATToolsDataBundleObject(
    var name: String = "",
    var bundle:  Map<String, DataOfferRequiredDataDefinitionBundleKeyV2>
) : Serializable