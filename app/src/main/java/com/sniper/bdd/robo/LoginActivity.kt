package com.sniper.bdd.robo

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity(), LoginPresenter.View {

    private val presenter = LoginPresenter(this, LoginValidator())

    private lateinit var password : EditText
    private lateinit var email : EditText
    private lateinit var signInButton : Button
    private lateinit var successfulLoginTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        password = findViewById(R.id.password)
        email = findViewById(R.id.email)
        signInButton = findViewById(R.id.sign_in_button)
        successfulLoginTextView = findViewById(R.id.successful_login_text_view)

        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        signInButton.setOnClickListener { attemptLogin() }
    }

    // Reset errors.
    override fun clearErrors() {
        email.error = null
        password.error = null
    }

    override fun displaySuccessfulLogin() {
        successfulLoginTextView.visibility = View.VISIBLE
    }

    override fun displayEmailLoginError() {
        successfulLoginTextView.visibility = View.GONE
        email.error = getString(R.string.error_invalid_email)
        email.requestFocus()
    }

    override fun displayPasswordLoginError() {
        successfulLoginTextView.visibility = View.GONE
        password.error = getString(R.string.error_invalid_password)
        password.requestFocus()
    }

    private fun attemptLogin() {
        presenter.onLoginSubmit(email.text.toString(), password.text.toString())
    }

}
