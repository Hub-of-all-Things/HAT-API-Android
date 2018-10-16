package com.hubofallthings.android.hatApi.objects.feed

import java.io.Serializable

data class HATFeedTitle(
        val action: String? = null,
        val text: String,
        val subtitle: String? = null
): Serializable