package com.sniper.bdd.robo.dsl

import com.sniper.bdd.robo.BaseRobot


/**
 * Use the method to setup GIVEN step for any test (Unit and Instrumentation)
 * @param block - the block of code which needs to be executed in the GIVEN step
 * @return - robot instance of the test robot, it allows us to call robot methods directly
 */
fun <T: BaseRobot>TestRun<T>.GIVEN(
    block: T.() -> Unit
): T {
    //you can print something here
    return robot.apply(block)
}

/**
 * Use the method to setup WHEN step for any test (Unit and Instrumentation)
 * @param block - the block of code which needs to be executed in the WHEN step
 * @return - robot instance of the unit test, it allows us to call robot methods directly
 */
fun <T: BaseRobot>TestRun<T>.WHEN(
    block: T.() -> Unit
): T {
    //you can print something here too
    return robot.apply(block)
}

/**
 * Use the method to setup AND step for any test (Unit and Instrumentation)
 * @param block - the block of code which needs to be executed in the AND step
 * @return - robot instance of the unit test, it allows us to call robot methods directly
 */
fun <T: BaseRobot>TestRun<T>.AND(
    block: T.() -> Unit
): T {
    //you can print something here too
    return robot.apply(block)
}

/**
 * Use the method to setup THEN step for any test (Unit and Instrumentation)
 * @param block - the block of code which needs to be executed in the THEN step
 * @return - robot instance of the unit test, it allows us to call robot methods directly
 */
fun <T: BaseRobot>TestRun<T>.THEN(
    block: T.() -> Unit
): T {
    //you can print something here too
    return robot.apply(block)
}

/**
 * Simple data class which represents the test it self. We can add as much properties we need
 */
data class TestRun<T: BaseRobot>(
    val robot: T,
    val isUnitTest: Boolean
)
