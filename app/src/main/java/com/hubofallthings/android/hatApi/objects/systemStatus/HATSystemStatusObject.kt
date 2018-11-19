package com.hubofallthings.android.hatApi.objects.systemStatus

data class HATSystemStatusObject(
        /// The title of the object
        var title: String = "",
                /// The kind object holding the values
        var kind: HATSystemStatusKindObject?
)