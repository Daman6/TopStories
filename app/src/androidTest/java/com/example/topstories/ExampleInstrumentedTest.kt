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
import com.example.topstories.model.data.ImageGist
import com.example.topstories.screens.VLTray01Header
import com.example.topstories.screens.VLTray01ItemText
import com.example.topstories.ui.theme.TopStoriesTheme
import com.google.gson.Gson
import com.viewlift.uimodule.data.*
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
    fun SeeMoreButtonTest() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)
            TopStoriesTheme {
                VLSeeMoreButton(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 28.dp)
                        .fillMaxWidth(), module = main
                )
            }
        }

        composeTestRule.onNodeWithTag("SEEMOREBTN").assertExists()
        composeTestRule.onNodeWithTag("SEEMOREBTN").assertTextEquals("See More")

    }


    @Test
    fun VLTrayHeader() {
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
    fun CheckNotNUll() {
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

    @Test
    fun CheckShareButtonVisibilityIsVisible() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = 1678898189,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = true,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "TOP",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                HighlightLeftImageTrayItem(
                    data = contentData,
                    layout = layout,
                    isShowDescription = false
                )
                VLTray01ItemText(contentData, 30.dp, layout)

            }
        }
        composeTestRule.onRoot().printToLog("TAG")
        val mainshareIcon = composeTestRule.onNodeWithTag("TOPSTORYMAINSHAREICON")
        mainshareIcon.assertExists()
        mainshareIcon.assertIsDisplayed()
        val shareIcon = composeTestRule.onNodeWithTag("SHAREICON")
        shareIcon.assertExists()
        shareIcon.assertIsDisplayed()
    }

    @Test
    fun CheckShareButtonVisibilityIsInvisible() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = 1678898189,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "TOP",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                HighlightLeftImageTrayItem(
                    data = contentData,
                    layout = layout,
                    isShowDescription = false
                )
                VLTray01ItemText(contentData, 30.dp, layout)

            }
        }
        val shareIcon = composeTestRule.onNodeWithTag("SHAREICON")
        shareIcon.assertDoesNotExist()

        val mainshareIcon = composeTestRule.onNodeWithTag("TOPSTORYMAINSHAREICON")
        mainshareIcon.assertDoesNotExist()
    }

    @Test
    fun CheckTopThumbailNailExist() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = 1678898189,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "TOP",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                val settings = layout?.settings
                when (settings?.thumbnailPlacement) {
                    "TOP" -> {
                        Log.e("hdbd", "TOP")
                        HighlightTopImageTrayItem(contentData, layout, false)
                    }
                    "LEFT" -> {
                        Log.e("hdbd", "LEFt")
                        HighlightLeftImageTrayItem(contentData, layout, false)
                    }
                    "RIGHT" -> {
                        Log.e("hdbd", "Rght")
                        HighlightRightImageTrayItem(contentData, layout, false)
                    }
                    else -> {
                        Log.e("hdbd", "ELSE")
                        HighlightLeftImageTrayItem(contentData, layout, false)
                    }
                }

            }
        }
        composeTestRule.onNodeWithTag("TOPTHUMBNAIL", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("TOPTHUMBNAIL", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("LEFTTHUMBNAIL", useUnmergedTree = true).assertDoesNotExist()

    }


    @Test
    fun CheckLeftThumbailNailExist() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = 1678898189,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "LEFT",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                val settings = layout?.settings
                when (settings?.thumbnailPlacement) {
                    "TOP" -> {
                        Log.e("hdbd", "TOP")
                        HighlightTopImageTrayItem(contentData, layout, false)
                    }
                    "LEFT" -> {
                        Log.e("hdbd", "LEFt")
                        HighlightLeftImageTrayItem(contentData, layout, false)
                    }
                    "RIGHT" -> {
                        Log.e("hdbd", "Rght")
                        HighlightRightImageTrayItem(contentData, layout, false)
                    }
                    else -> {
                        Log.e("hdbd", "ELSE")
                        HighlightLeftImageTrayItem(contentData, layout, false)
                    }
                }

            }
        }
        composeTestRule.onNodeWithTag("TOPTHUMBNAIL", useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag("LEFTTHUMBNAIL", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("LEFTTHUMBNAIL", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun CheckRightThumbailNailExist() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = 1678898189,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "RIGHT",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                val settings = layout?.settings
                when (settings?.thumbnailPlacement) {
                    "TOP" -> {
                        Log.e("hdbd", "TOP")
                        HighlightTopImageTrayItem(contentData, layout, false)
                    }
                    "LEFT" -> {
                        Log.e("hdbd", "LEFt")
                        HighlightLeftImageTrayItem(contentData, layout, false)
                    }
                    "RIGHT" -> {
                        Log.e("hdbd", "Rght")
                        HighlightRightImageTrayItem(contentData, layout, false)
                    }
                    else -> {
                        Log.e("hdbd", "ELSE")
                        HighlightLeftImageTrayItem(contentData, layout, false)
                    }
                }

            }
        }
        composeTestRule.onNodeWithTag("TOPTHUMBNAIL", useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag("LEFTTHUMBNAIL", useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag("RIGHTTHUMBNAIL", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithTag("RIGHTTHUMBNAIL", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun CheckShowDurationIsVisibleWithTimeAndAuthor() {
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = 1678898189,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "RIGHT",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                HighlightTopImageTrayItem(contentData, layout, false)
            }
        }
        composeTestRule.onRoot().printToLog("TAG")
        val textNode = composeTestRule.onNodeWithTag("TOPSTORIESINFOTEXT",useUnmergedTree = true)
        textNode.assertExists()
        textNode.assertTextEquals("21d • Christian Plank")
    }

    @Test
    fun CheckShowDurationIsVisibleWithTimeOnly(){
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = 1678898189,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "RIGHT",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                HighlightTopImageTrayItem(contentData, layout, false)
            }
        }
        composeTestRule.onRoot().printToLog("TAG")
        val textNode = composeTestRule.onNodeWithTag("TOPSTORIESINFOTEXT",useUnmergedTree = true)
        textNode.assertExists()
        textNode.assertTextEquals("21d")
    }

    @Test
    fun CheckShowDurationIsVisibleWithAuthorNameOnly(){
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = null,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "RIGHT",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                HighlightTopImageTrayItem(contentData, layout, false)
            }
        }
        composeTestRule.onRoot().printToLog("TAG")
        val textNode = composeTestRule.onNodeWithTag("TOPSTORIESINFOTEXT",useUnmergedTree = true)
        textNode.assertExists()
        textNode.assertTextEquals("0.7 • Christian Plank")
    }
    @Test
    fun CheckZeroItemInTopStory(){
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = null,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "RIGHT",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                VLVerticalGrid(dataList = emptyList(), layout = layout)
            }
        }
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithTag("VERTICALGRID")
            .onChildren()
            .assertCountEquals(0)
    }
    @Test
    fun CheckTwoItemInTopStory(){
        composeTestRule.setContent {
            val context = LocalContext.current
            val jsonFile = context.assets.open("data.json")
            val json = jsonFile.bufferedReader().use { it.readText() }
            Log.d("jso", json)

            val gson = Gson()
            val main = gson.fromJson(json, Module::class.java)

            val imageGist = com.viewlift.uimodule.data.ImageGist(
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/15/1678898169667_tts1_16x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913352713_9_16changeimage_13_1x1Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913180800_32_9changeimage_12_32x9Images.jpg",
                "https://image.develop.monumentalsportsnetwork.com/00000151-11b4-d29b-a17d-55fdb2b80000/images/2023/03/4/1677913339325_3_4changeimage_12_3x4Images.jpg",
                "",
                ""
            )
            val gist = Gist(
                "ARTICLE",
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                imageGist = imageGist,
                "/article/New article-20230304065855037",
                "Wizards Hopeful Beal can Key Turnaround",
                "",
                null
            )
            val highlights = Highlights("22", "Higlight", gist)
            val liveStream: ArrayList<Highlights> = arrayListOf(highlights)
            val scheduleEvent: ArrayList<ScheduleEvent> =
                arrayListOf(ScheduleEvent(null, null, null))
            val metaData: ArrayList<com.viewlift.uimodule.data.Metadata> =
                arrayListOf(com.viewlift.uimodule.data.Metadata("metadata", "value"))
            val contentData = ContentData(
                author = "Christian Plank",
                runtime = 0,
                contentType = "ARTICLE",
                highlights = liveStream,
                homeTeam = null,
                id = "fcfac5ab-6e86-44fb-9aab-59db34a71e64",
                publishDate = null,
                readTime = "0.7",
                score = null,
                gist = gist,
                awayTeam = null,
                parentalRating = "",
                livestreams = liveStream,
                preview = highlights,
                currentState = "",
                states = null,
                metadata = metaData,
                schedules = scheduleEvent,
                broadcaster = ""
            )
            val contentDataList = listOf<ContentData>(contentData,contentData)
            val metadata = com.viewlift.uimodule.data.MetadataMap(
                "Quarter",
                "Final",
                "GAME PREVIEW",
                "Game starts at",
                "LIVE",
                "2nd Period",
                "POST GAME",
                "Presented by:",
                "PRE GAME",
                "Read Story",
                "See More",
                "Watch in Game Center"
            )
            val layout = Layout(
                blockName = "VL_HighlightTray_01",
                id = "",
                fontSetting = FontsSetting(
                    titleFontFamilyName = "AcuminPro",
                    titleFontSize = "18px",
                    titleFontWeight = "bold",
                    trayItemFontWeight = "semiBold",
                    trayItemSubtitleFontSize = "14px",
                    subtitleFontSize = "",
                    subtitleFontWeight = "",
                    subtitleFontFamilyName = "",
                    trayItemFontFamilyName = "",
                    buttonFontFamilyName = "",
                    buttonFontSize = "",
                    buttonFontWeight = "",
                    teamFontFamilyName = "",
                    teamFontSize = "",
                    teamFontWeight = "",
                    trayItemTitleFontFamilyName = "",
                    trayItemTitleFontSize = "",
                    trayItemTitleFontWeight = ""
                ),
                settings = Settings(
                    columns = Columns(4.0, 4.0, 6.0, 4.0),
                    enableCustomStyle = false,
                    enableOverrideSettings = false,
                    enableSharing = false,
                    loop = false,
                    parallax = Parallax(false, "", ""),
                    removeTrayBottomTitle = false,
                    roundedCornerButton = false,
                    seeAll = false,
                    seeAllCard = false,
                    showContentDuration = false,
                    showMore = true,
                    showMorePermalink = "/category/wizards",
                    textBackgroundColor = TextBackgroundColor(1.0f, 65.0f, 29.0f, 13.0f),
                    thumbnailPlacement = "RIGHT",
                    thumbnailType = "16*9",
                    trayIconUrl = "",
                    fontSettings = listOf(),
                    seeAllPermalink = "",
                    showMoreCTA = "",
                    postGameColor = "",
                    defaultGameColor = "",
                    liveGameColor = "",
                    calandarBgColor = "",
                    preGameColor = "",
                    moduleBackgroundColor = "",
                    trayTitleColor = ""
                ),
                isMasthead = false,
                styles = Styles(""),
                type = "VL_HighlightTray_01",
                view = "VL_HighlightTray_01"
            )
            main.copy(
                contentData = contentDataList,
                id = "f5932976-8aea-4bbd-961b-8b998c622628",
                title = "Top Stories",
                subtitle = "",
                moduleType = "CuratedTrayModule",
                metadataMap = null,
                layout = layout
            )

            TopStoriesTheme {
                VLVerticalGrid(dataList = contentDataList, layout = layout)
            }
        }
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onNodeWithTag("VERTICALGRID")
            .onChildren()
            .assertCountEquals(2)
    }
}