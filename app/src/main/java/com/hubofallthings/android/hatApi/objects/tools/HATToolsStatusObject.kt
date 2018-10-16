package com.hubofallthings.hatappandroid.`object`.tools

import java.io.Serializable

data class HATToolsStatusObject(
    var available: Boolean = false,
    var enabled: Boolean = false,
    var lastExecution: String?,
    var executionStarted: String?
): Serializable