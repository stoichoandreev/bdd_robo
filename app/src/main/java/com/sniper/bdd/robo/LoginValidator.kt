package com.sniper.bdd.robo

import android.text.TextUtils


class LoginValidator {

    fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && email.contains("@")
    }

    fun isPasswordValid(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length > 6
    }

}
