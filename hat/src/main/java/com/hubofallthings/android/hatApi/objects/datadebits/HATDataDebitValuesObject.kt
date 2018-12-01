package com.hubofallthings.android.hatApi.objects.datadebits

import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferRequiredDataDefinitionBundleKeyEndpointsV2

data class HATDataDebitValuesObject(
        var conditions : ArrayList<Map<String,Boolean>>?,
        var bundle : ArrayList<Map<String,DataOfferRequiredDataDefinitionBundleKeyEndpointsV2>>?
)