package com.sniper.bdd.robo.sharedtest.runner

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.sniper.bdd.robo.sharedtest.MyTestApplication


class MyInstrumentationTestRunner : AndroidJUnitRunner() {

    companion object {
        var isRobolectricRun = true
    }

    init {
        System.setProperty("robolectric.invokedynamic.enable", "false")
    }

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        isRobolectricRun = false//If the runner is used, this means that Espresso run has been triggered, otherwise is Robolectric JVM run
    }

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, MyTestApplication::class.java.canonicalName, context)
    }
}
