package com.example.moviesapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoritesFlowTest {

    @get:Rule
    val composeRule =
        createAndroidComposeRule<MainActivity>()

    @Test
    fun addingMovieUpdatesFavoritesScreen() {

        composeRule
            .onNodeWithContentDescription(
                "Add Fight Club to favorites"
            )
            .performClick()

        composeRule
            .onNodeWithText("Favorites")
            .performClick()

        composeRule
            .onNodeWithText("Fight Club")
            .assertIsDisplayed()
    }
}
