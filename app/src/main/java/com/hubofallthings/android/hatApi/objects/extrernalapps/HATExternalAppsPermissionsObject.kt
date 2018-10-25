package com.hubofallthings.android.hatApi.objects.extrernalapps

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.hubofallthings.android.hatApi.objects.dataoffers.DataOfferRequiredDataDefinitionObjectV2
import java.io.Serializable

data class HATExternalAppsPermissionsObject(
        // MARK: - Variables

        // Any roles granted with this app
        val rolesGranted: Array<HATExternalAppsPermissionsRolesGrantedObject>? = null,
        // The bundle info required for this app
        val dataRequired: HATExternalAppsDataRequiredObject? = null,
        // The bundle info required for this app
        @JsonSetter(nulls = Nulls.AS_EMPTY)
        val dataRetrieved: DataOfferRequiredDataDefinitionObjectV2? = null
): Serializable