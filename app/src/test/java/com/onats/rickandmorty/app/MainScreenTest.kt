package com.onats.rickandmorty.app

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.onats.rickandmorty.app.utils.TestTags
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `test that bottom navigation is displayed`() {
        composeTestRule.setContent {
            val navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreen(navController = navController)
        }

        composeTestRule.onNodeWithTag(TestTags.BOTTOM_NAV_BAR, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.CHARACTERS_SCREEN, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.EPISODES_SCREEN, useUnmergedTree = true)
            .assertIsNotDisplayed()
    }

    @Test
    fun `test that bottom navigation can navigate the displayed screen`() {
        composeTestRule.setContent {
            val navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreen(navController = navController)
        }

        composeTestRule.onNodeWithContentDescription("episodes_navigation_bar_item").performClick()
        composeTestRule.onNodeWithTag(TestTags.CHARACTERS_SCREEN, useUnmergedTree = true)
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(TestTags.EPISODES_SCREEN, useUnmergedTree = true)
            .assertIsDisplayed()
    }
}