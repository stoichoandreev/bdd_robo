package com.sniper.bdd.robo


class LoginPresenter(
    private val view: View,
    private val validator: LoginValidator
) {
    interface View {

        fun clearErrors()

        fun displaySuccessfulLogin()

        fun displayEmailLoginError()

        fun displayPasswordLoginError()

    }

    fun onLoginSubmit(
        email: String,
        password: String
    ) {
        view.clearErrors()

        val isEmailOK = validator.isEmailValid(email)
        val isPasswordOK = validator.isPasswordValid(password)

        when {
            isPasswordOK && isEmailOK -> view.displaySuccessfulLogin()
            !isEmailOK -> view.displayEmailLoginError()
            !isPasswordOK -> view.displayPasswordLoginError()
        }
    }
}