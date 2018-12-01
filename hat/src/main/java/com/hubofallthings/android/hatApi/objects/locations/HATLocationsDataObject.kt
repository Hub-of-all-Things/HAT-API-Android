package com.hubofallthings.android.hatApi.objects.locations

data class HATLocationsDataObject (
        val latitude: Double?,
        val longitude: Double?,
        val horizontalAccuracy: Number?,
        val verticalAccuracy: Number?,
        val dateCreated: Int?,
        val dateCreatedLocal: String?,
        val speed: Number?,
        val altitude: Number?,
        val course: Number?
)