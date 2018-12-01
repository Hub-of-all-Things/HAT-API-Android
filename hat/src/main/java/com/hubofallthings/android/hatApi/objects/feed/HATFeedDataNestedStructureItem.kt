package com.hubofallthings.android.hatApi.objects.feed

import java.io.Serializable


data class HATFeedDataNestedStructureItem(
        val content : String,
        val badge : String? = null,
        val types : Array<String>? = null
): Serializable