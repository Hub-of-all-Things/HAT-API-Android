package com.hubofallthings.android.hatApi.objects.tools

import java.io.Serializable

data class HATToolsStatusObject(
    var available: Boolean = false,
    var enabled: Boolean = false,
    var lastExecution: String?,
    var executionStarted: String?
) : Serializable