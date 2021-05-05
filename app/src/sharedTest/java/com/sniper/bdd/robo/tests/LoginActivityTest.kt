package com.sniper.bdd.robo.tests

import android.os.Bundle
import org.junit.Test
import org.junit.Before
import org.junit.After
import org.junit.runner.RunWith
import androidx.test.core.app.ActivityScenario
import com.sniper.bdd.robo.data.DataFactory
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sniper.bdd.robo.*
import com.sniper.bdd.robo.dsl.*
import com.sniper.bdd.robo.dsl.AND
import com.sniper.bdd.robo.dsl.GIVEN
import com.sniper.bdd.robo.dsl.WHEN
import org.hamcrest.CoreMatchers.not
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(AndroidJUnit4::class)//Be careful with the import here, do not import androidx.test.runner.AndroidJUnit4! You will need androidx.test.ext.junit.runners.AndroidJUnit4
@Config(sdk = [24], application = MyTestApplication::class)
@LooperMode(LooperMode.Mode.PAUSED)
class LoginActivityTest {

    private val robot = Robot()

    @Before
    fun setup() {
        robot.setup()
    }

    @After
    fun tearsDown() {
        robot.tearsDown()
    }

    @Test
    fun shouldDisplayPasswordLoginError() {
        RUN_UI_TEST(robot) {
            GIVEN { createLoginActivity() }
            WHEN { enterEmail(DataFactory.EMAIL) }
            AND { enterPassword(DataFactory.INVALID_PASSWORD) }
            AND { clickSubmitLogin() }
            THEN { checkSuccessfulLoginNotDisplayed() }
            THEN { checkPasswordErrorDisplayed() }
        }
    }

    @Test
    fun shouldDisplaySuccessfulLogin() {
        RUN_UI_TEST(robot) {
            GIVEN { createLoginActivity() }
            WHEN { enterEmail(DataFactory.EMAIL) }
            AND { enterPassword(DataFactory.VALID_PASSWORD) }
            AND { clickSubmitLogin() }
            THEN { checkSuccessfulLogin() }
        }
    }

    private class Robot: BaseRobot() {

        var activityScenario: ActivityScenario<LoginActivity>? = null

        override fun tearsDown() {
            activityScenario?.close()
        }

        fun createLoginActivity(args: Bundle? = null) {
            activityScenario = ActivityScenario.launch(LoginActivity::class.java, args)
        }

        fun enterEmail(someEmail: String) {
            Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText(someEmail))
        }

        fun enterPassword(somePassword: String) {
            Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText(somePassword))
        }

        fun clickSubmitLogin() {
            Espresso.onView(ViewMatchers.withId(R.id.sign_in_button)).perform(ViewActions.click())
        }

        fun checkSuccessfulLogin() {
            Espresso.onView(ViewMatchers.withId(R.id.successful_login_text_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun checkSuccessfulLoginNotDisplayed() {
            Espresso.onView(ViewMatchers.withId(R.id.successful_login_text_view)).check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
        }

        fun checkPasswordErrorDisplayed() {
            val expectedErrorMessage: String = InstrumentationRegistry.getInstrumentation().targetContext.getString(R.string.error_invalid_password)
            Espresso.onView(ViewMatchers.withId(R.id.password)).check(ViewAssertions.matches(ViewMatchers.hasErrorText(expectedErrorMessage)))
        }

    }
}