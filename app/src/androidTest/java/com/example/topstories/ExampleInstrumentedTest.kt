package com.example.topstories

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.topstories.screens.VLTray01Header
import com.example.topstories.ui.theme.TopStoriesTheme
import com.google.gson.Gson
import com.viewlift.uimodule.data.Module
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
    }

    @Test
    fun SeeMoreButtonTest(){
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)
            TopStoriesTheme {
                VLSeeMoreButton( modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 28.dp)
                    .fillMaxWidth(),module = main)
            }
        }

        composeTestRule.onNodeWithTag("SEEMOREBTN").assertExists()
        composeTestRule.onNodeWithTag("SEEMOREBTN").assertTextEquals("See More")

    }



    @Test
    fun VLTrayHeader(){
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)
            Log.e("ndjnd 1", main.title.toString())


            TopStoriesTheme {
                VLTray01Header(
                    title = main.title ?: "",
                    subtitle = main.subtitle,
                    layout = main.layout,
                    trayTitleColor = Color.White,
                    isDivider = false,
//            listener = listener
                )
            }
        }

        val textNode = composeTestRule.onNodeWithTag("TITLETAG")
        textNode.assertExists()
        textNode.assertTextEquals("Top Stories")//Correct Text As per design
    }

    @Test
    fun CheckNotNUll(){
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)
            Log.e("ndjnd 1", main.title.toString())


            TopStoriesTheme {
                VLTray01Header(
                    title = "",
                    subtitle = main.subtitle,
                    layout = main.layout,
                    trayTitleColor = Color.White,
                    isDivider = false,
//            listener = listener
                )
            }
        }

        val textNode = composeTestRule.onNodeWithTag("TITLETAG")
        textNode.assertExists()
        textNode.assertTextEquals("")//Value is null
    }

}