package com.groupd.keltis.cheating;

import com.groupd.keltis.accelerometer.ShakeDetectorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CheatTest.class,
        ShakeDetectorTest.class
})

//test suite used to start all cheat tests
public class CheatTestSuite {
}
