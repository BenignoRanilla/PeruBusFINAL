package com.ruta.perubus.helper

import java.util.regex.Matcher
import java.util.regex.Pattern

class Utils {


    fun isValidEmailAddress(emailAddress: String?): Boolean {
        val emailRegEx: String
        val pattern: Pattern
        // Regex for a valid email address
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$"
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx)
        val matcher: Matcher = pattern.matcher(emailAddress)
        return if (!matcher.find()) {
            false
        } else true
    }
}