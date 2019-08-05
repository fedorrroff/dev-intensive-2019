package ru.skillbranch.devintensive.utils

import android.os.Build
import androidx.annotation.RequiresApi

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val (firstName, lastName) = when {
            fullName.isNullOrBlank() -> Pair(null, null)
            else -> {
                val parts: List<String>? = fullName.split(" ")
                Pair(parts?.getOrNull(0), parts?.getOrNull(1))
            }
        }
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()){
            return null
        }
        else if (firstName.isNullOrBlank()){
            return lastName?.get(0)?.toUpperCase().toString()
        }
        else if (lastName.isNullOrBlank()) {
            return firstName[0].toUpperCase().toString()
        }
        else return (firstName[0].toString().toUpperCase() +  lastName[0].toString().toUpperCase())
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun transliteration(payload: String, divider: String = " "): String {

        val leters: Map<String, String> = mapOf("а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya",
            " " to divider
        )
        var newName = payload.trim()

        for (i in newName){
            if (i.isUpperCase()) {
                newName = newName.replace(i.toString(), leters.getOrDefault(i.toString().toLowerCase(), i.toString()).capitalize())
            } else {
                newName = newName.replace(i.toString(), leters.getOrDefault(i.toString(), i.toString()))
            }
        }

        return newName
    }
}
