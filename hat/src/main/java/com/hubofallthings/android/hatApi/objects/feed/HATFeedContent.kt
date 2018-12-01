package com.hubofallthings.android.hatApi.objects.feed

import java.io.Serializable


data class HATFeedContent(val text: String? = null,
                          val html: String? = null,
                          val media: Array<HATFeedMedia>? = null,
                          val nestedStructure : List<Map<String , Array<HATFeedDataNestedStructureItem>>>? = null
): Serializable