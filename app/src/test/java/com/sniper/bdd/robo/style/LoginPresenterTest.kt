package com.sniper.bdd.robo.style

import com.sniper.bdd.robo.BaseRobot
import com.sniper.bdd.robo.LoginPresenter
import com.sniper.bdd.robo.LoginValidator
import com.sniper.bdd.robo.data.DataFactory
import com.sniper.bdd.robo.dsl.GIVEN
import com.sniper.bdd.robo.dsl.RUN_UNIT_TEST
import com.sniper.bdd.robo.dsl.THEN
import com.sniper.bdd.robo.dsl.WHEN
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test


class LoginPresenterTest {

    private val robot = Robot()

    @Before
    fun setup() {
        robot.setup()
    }

    @Test
    fun `shouldClearErrorsOnLogin`() {
        RUN_UNIT_TEST(robot) {
            WHEN { loginSubmit() }
            THEN { checkViewClearErrors() }
        }
    }

    @Test
    fun `shouldDisplaySuccessfulLogin`() {
        RUN_UNIT_TEST(robot) {
            GIVEN { stubValidEmail() }
            GIVEN { stubValidPassword() }
            WHEN { loginSubmit() }
            THEN { checkViewDisplaySuccessfulLogin() }
        }

    }

    private class Robot: BaseRobot() {
        @RelaxedMockK
        private lateinit var view: LoginPresenter.View
        @RelaxedMockK
        private lateinit var validator: LoginValidator

        private lateinit var tested: LoginPresenter

        override fun setup() {
            MockKAnnotations.init(this)
            tested = LoginPresenter(view, validator)
        }

        fun stubValidEmail() {
            every { validator.isEmailValid(any()) }.returns(true)
        }

        fun stubValidPassword() {
            every { validator.isPasswordValid(any()) }.returns(true)
        }

        fun loginSubmit() {
            tested.onLoginSubmit(DataFactory.EMAIL, DataFactory.VALID_PASSWORD)
        }

        fun checkViewClearErrors() {
            verify { view.clearErrors() }
        }

        fun checkViewDisplaySuccessfulLogin() {
            verify { view.displaySuccessfulLogin() }
        }
    }
}