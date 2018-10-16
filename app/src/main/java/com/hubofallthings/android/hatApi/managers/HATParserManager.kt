package com.hubofallthings.android.hatApi.managers

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException


open class HATParserManager{
    @Throws(IOException::class)
    fun <T> jsonToObjectList(json: String, tClass: Class<T>): List<T> {
        val mapper = jacksonObjectMapper()
        mapper.configOverride(String::class.java).setterInfo = JsonSetter.Value.forValueNulls(Nulls.FAIL)
        mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY))
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return mapper.readValue(json, mapper.typeFactory.constructCollectionType(ArrayList::class.java, tClass))
    }
}
inline fun <reified T: Any?> String.toKotlinObject(): T {
    val mapper = jacksonObjectMapper()
    mapper.configOverride(String::class.java).setterInfo = JsonSetter.Value.forValueNulls(Nulls.FAIL)
    mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY))
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    return mapper.readValue(this, T::class.java)
}

