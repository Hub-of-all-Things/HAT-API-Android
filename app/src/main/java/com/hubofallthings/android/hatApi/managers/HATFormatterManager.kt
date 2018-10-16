package com.hubofallthings.android.hatApi.Managers

class HATFormatterManager {

    fun fromBase64URLToBase64(stringToConvert: String): String {

        var convertedString = stringToConvert
        if (convertedString.count() % 4 == 2) {

            convertedString += "=="
        } else if (convertedString.count() % 4 == 3) {

            convertedString += "="
        }

        convertedString = convertedString.replace("-", "+", true)
        convertedString = convertedString.replace("_", "/", true)

        return  convertedString
    }
}