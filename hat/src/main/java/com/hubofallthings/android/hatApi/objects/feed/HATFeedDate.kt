package com.hubofallthings.android.hatApi.objects.feed

import java.io.Serializable

data class HATFeedDate(
        val iso: String,
        val unix: Number
): Serializable