# Android BDD with Kotlin, Robolectric and Espresso
This is a playground repository to demonstrate the ability of having same code style in Unit and Instrumentation tests. 
As a bonus in the repo we also have setup of running our Instrumentation tests on JVM and also on ART (real device or emulator)

To run the Instrumentation tests on JVM execute:
`./gradlew testDebugUnitTest --tests com.sniper.bdd.robo.tests.LoginActivityTest`

To run the Instrumentation tests on ART(device or emulator) execute:
`./gradlew connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.sniper.bdd.robo.tests.LoginActivityTest`