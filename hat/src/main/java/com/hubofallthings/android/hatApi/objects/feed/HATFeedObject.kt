package com.hubofallthings.android.hatApi.objects.feed

import java.io.Serializable


data class HATFeedObject(
        val date: HATFeedDate,
        val source: String,
        val content: HATFeedContent? = null,
        val location: HATFeedLocation? = null,
        val title: HATFeedTitle? = null,
        val types: Array<String>
): Serializable
