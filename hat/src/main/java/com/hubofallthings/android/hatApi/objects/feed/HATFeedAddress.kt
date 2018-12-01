package com.hubofallthings.android.hatApi.objects.feed

import java.io.Serializable

data class HATFeedAddress(
        val city: String? = null,
        val country: String? = null,
        val name: String? = null,
        val street: String? = null,
        val zip: String? = null
): Serializable