package com.deprecabile.appsleeper

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.textAsString
import androidx.test.uiautomator.uiAutomator
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityLaunchTest {
    @Test
    fun appStartsAndShowsItsTitle() {
        ActivityScenario.launch(MainActivity::class.java).use {
            uiAutomator {
                onElement { textAsString() == "AppSleeper" }
            }
        }
    }
}
