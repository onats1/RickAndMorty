package com.onats.rickandmorty.app.bottomnavbar

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NavigationBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `bottom nav bar displays correct items`() {
        composeTestRule.setContent {
            val navController = TestNavHostController(LocalContext.current)
            BottomNavBar(navController = navController)
        }

        composeTestRule.onNodeWithText("Characters").assertIsDisplayed()
        composeTestRule.onNodeWithText("Episodes").assertIsDisplayed()
    }

    @Test
    fun `bottom nav bar displays correct number of items`() {
        composeTestRule.setContent {
            val navController = TestNavHostController(LocalContext.current)
            BottomNavBar(navController = navController)
        }

        val items = composeTestRule.onAllNodesWithContentDescription("navigation_bar_item", substring = true, useUnmergedTree = true)
        items.assertCountEquals(2)
    }

    @Test
    fun `selected bottom nav item is marked as selected`() {
        composeTestRule.setContent {
            val navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            BottomNavBar(navController = navController)
        }

        composeTestRule.onNodeWithText("Characters")
            .assertIsSelected()

        composeTestRule.onNodeWithText("Episodes")
            .assertIsNotSelected()

        composeTestRule.onNodeWithText("Episodes").performClick()

        composeTestRule.onNodeWithText("Characters")
            .assertIsNotSelected()

        composeTestRule.onNodeWithText("Episodes")
            .assertIsSelected()
    }
}