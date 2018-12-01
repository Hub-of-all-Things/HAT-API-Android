package com.hubofallthings.android.hatApi.objects


/// The operator JSON format
data class Operator(
        var `operator`: String = "between",
        var lower: Int = 0,
        var upper: Int = 0
)
/// The filter JSON format
data class Filter(
        var field: String = "",
        var `operator`: Operator = Operator(),
        var transformation: Transformation? = null
)
/// The filter JSON format
data class Transformation(
        var transformation: String? = null,
        var part: String? = null
)
/// The combinator JSON format
data  class BodyRequest  (
        var endpoint: String = "rumpel/locations/android",
        var filters: ArrayList<Filter> = arrayListOf(Filter())
)