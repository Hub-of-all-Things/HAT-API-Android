package com.hubofallthings.android.hatApi.objects.feed

import java.io.Serializable


data class HATFeedLocation(val address: HATFeedAddress? = null,
                           val geo: HATFeedLocationGeo? = null,
                           val tags: Array<String>? = null): Serializable