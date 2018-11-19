package com.hubofallthings.android.hatApi.objects.systemStatus

data class HATSystemStatusKindObject(
        /// The value of the object
         var metric: Any? = "",
                /// The kind of the value of the object
         var kind: String? = "",
                /// The unit type of the value of the object
         var units: String? = ""
)