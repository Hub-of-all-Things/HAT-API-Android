package com.hubofallthings.android.hatApi.objects.datadebits

import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferRequiredDataDefinitionObjectV2

data class HATDataDebitCreationObject(
        var dataDebitKey: String = "",
        var purpose: String = "",
        var start: String = "",
        var period: Double = 0.0,
        var termsUrl: String = "",
        var cancelAtPeriodEnd: Boolean = false,
        var requestClientName: String = "",
        var requestClientUrl: String = "",
        var requestClientLogoUrl: String = "",
        var requestApplicationId: String? = "",
        var requestDescription: String? = "",
        var conditions: DataOfferRequiredDataDefinitionObjectV2?,
        var bundle: DataOfferRequiredDataDefinitionObjectV2
)