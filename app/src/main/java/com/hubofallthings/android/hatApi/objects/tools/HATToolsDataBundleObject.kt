package com.hubofallthings.hatappandroid.`object`.tools

import com.hubofallthings.hatappandroid.`object`.dataoffers.DataOfferRequiredDataDefinitionBundleKeyV2
import java.io.Serializable

data class HATToolsDataBundleObject(

    var name: String = "",
    var bundle:  Map<String, DataOfferRequiredDataDefinitionBundleKeyV2>
): Serializable