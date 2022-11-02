package com.sniper.bdd.robo.nostyle

import com.sniper.bdd.robo.LoginPresenter
import com.sniper.bdd.robo.LoginValidator
import com.sniper.bdd.robo.sharedtest.data.DataFactory
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test


class LoginPresenterTest {

    @RelaxedMockK
    private lateinit var view: LoginPresenter.View
    @RelaxedMockK
    private lateinit var validator: LoginValidator

    private lateinit var tested: LoginPresenter

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        tested = LoginPresenter(view, validator)
    }

    @Test
    fun `should Clear Errors On Login`() {
        //when
        tested.onLoginSubmit(DataFactory.EMAIL, DataFactory.VALID_PASSWORD)
        //then
        verify { view.clearErrors() }
    }

    @Test
    fun `should Display Successful Login`() {
        //given
        every { validator.isEmailValid(any()) }.returns(true)
        every { validator.isPasswordValid(any()) }.returns(true)
        //when
        tested.onLoginSubmit(DataFactory.EMAIL, DataFactory.VALID_PASSWORD)
        //then
        verify { view.displaySuccessfulLogin() }
    }
}