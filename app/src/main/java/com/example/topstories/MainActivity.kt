package com.example.topstories

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.wear.compose.material.CardDefaults
import coil.compose.AsyncImage
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.example.topstories.screens.VLTray01Header
import com.example.topstories.screens.VLTray01ItemText
import com.example.topstories.screens.VLTray01ViewItem
import com.example.topstories.ui.theme.TopStoriesTheme
import com.google.gson.Gson
import com.viewlift.uimodule.data.ContentData
import com.viewlift.uimodule.data.Layout
import com.viewlift.uimodule.data.Module
import com.viewlift.uimodule.tray.util.TrayUtil
import com.viewlift.uimodule.utils.UiUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopStoriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.DarkGray
                ) {
                    val context = LocalContext.current
                    val jsonFile = context.assets.open("data.json")
                    val json = jsonFile.bufferedReader().use { it.readText() }
                    Log.d("jso", json)

                    val gson = Gson()
                    val main = gson.fromJson(json, Module::class.java)

//                    VLTray01(module = main)
                    VLHighlightTray01(module = main)
                }
            }
        }
    }
}


@Composable
fun VLHighlightTray01(
    module: Module,
//    listener: TrayActionListener
) {
    val settings = module.layout?.settings
    val isParallax = TrayUtil.isParallax(settings)
    val verticalMargin = if (isParallax) 40.dp else 8.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val highlightWidth = (screenWidth-32.dp)
    val highlightHeight = highlightWidth.value /1.77
    var placeholder: MemoryCache.Key? = null
    val imageDpSize: DpSize = DpSize(highlightWidth,highlightHeight.dp)
    val topItemData = module.contentData[0]
    val imageUrl = TrayUtil.trayImage("16*9", topItemData.gist?.imageGist!!, imageDpSize)


    //val dataList = module.contentData.subList(1,module.contentData.lastIndex)
    val dataList = TrayUtil.getAvailableData(settings,module.contentData,true)

    ConstraintLayout(modifier = Modifier.padding(top = 16.dp)) {
        val (bgParallaxImage,trayContent) = createRefs()
        if (isParallax) {
            val imageUrl = settings?.parallax?.appsParallaxImageUrl
            println(" isParallax $verticalMargin $isParallax  $imageUrl")
            TrayParallaxImage(
                imageUrl = imageUrl,
//                resPlaceholder = TrayUtil.placeholderMap.get(settings?.thumbnailType)!!,
                modifier = Modifier.constrainAs(bgParallaxImage){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = verticalMargin)
                .constrainAs(trayContent) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            horizontalAlignment = Alignment.CenterHorizontally) {

           item {
               VLTray01Header(
                   title = module.title ?: "",
                   subtitle = module.subtitle,
                   layout = module.layout,
                   trayTitleColor = Color.White,
                   isDivider = false,
//                listener = listener
               )
               Spacer(modifier = Modifier.size(16.dp))
               HighlightTopImageTrayItem(topItemData, module.layout, false)
               Spacer(modifier = Modifier.size(16.dp))
               VLVerticalGrid(dataList = dataList, layout = module.layout)

               if (module.layout?.settings?.showMore == true) {
                   VLSeeMoreButton(
                       modifier = Modifier
                           .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 28.dp)
                           .fillMaxWidth(), module
                   )
                   /*Button(
                    onClick = { *//*TODO*//* },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "See all")
                }*/
               }
           }
            //Spacer(modifier = Modifier.size(16.dp))
        }
    }

}


@Composable
fun HighlightTopImageTrayItem(
    data: ContentData,
    layout: Layout?,
//    listener: TrayActionListener,
    isShowDescription: Boolean
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val highlightWidth = (screenWidth - 32.dp)
    val highlightHeight = highlightWidth.value / 1.49
    //val highlightHeight = highlightWidth.value / 3.55
    val imageDpSize: DpSize = DpSize(highlightWidth, highlightHeight.dp)
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(TrayUtil.TryaBgColor(layout?.settings), RoundedCornerShape(4.dp))
            .clickable {
//                listener.clickItemTrayAction(data.gist?.id + " -- " + data.gist?.title)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TrayImageTopThumb(topItemData = data, imageDpSize)
        VLTray01ItemText(data, imageDpSize.width, layout)
        /*if (data.gist?.contentType.equals("Playlist",true)){
            VLTrayPlayListItemText(
                data = data,
                trayType = trayType,
                settings = settings
            )
        }*/

        if (isShowDescription && data.gist!=null && !data.gist.description.isNullOrEmpty()) {
            Text(
                text = "Hbjfjsdhfjd fjbhjff kjebf efjbef ewf  fn bnew f kjbqf mn qf   kwbfk ef kbwef ew f",
                style = TrayUtil.TypoGraphyMap["traySubTitle"]!!.copy(color = Color.White),
                modifier = Modifier
                    .testTag("INSIDETOPHIGHLIGHT")
                    .padding(horizontal = 12.dp)
                    .width(highlightWidth)
            )
        }

        val publishedDate = if(data.publishDate!=null){
            "${UiUtils.getPublishDate(data.publishDate)}"
        } else {
            data.readTime
        }

        val infoText = TrayUtil.getAuthorName(publishedDate, data.author) // Todo add publish time and Author
        Text(
            text = infoText,
            style = TrayUtil.TypoGraphyMap["traySubTitle"]!!.copy(color = Color(0xff838996)),
            modifier = Modifier
                .testTag("TOPSTORIESINFOTEXT")
                .width(highlightWidth)
                .padding(12.dp)
        )
    }
}

@Composable
fun TrayImageTopThumb(topItemData : ContentData, imageDpSize: DpSize){

    var placeholder: MemoryCache.Key? = null

    //val topItemData = module.contentData[0]
    val imageUrl = TrayUtil.trayImage("16*9", topItemData.gist?.imageGist!!, imageDpSize)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
//        placeholder = painterResource(id = TrayUtil.placeholderMap.get("16*9")!!),
//        error = painterResource(id = TrayUtil.placeholderMap.get("16*9")!!),
        onSuccess = { placeholder = it.result.memoryCacheKey },
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .testTag("TOPTHUMBNAIL")
            .size(imageDpSize)
            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
    )
}


@Composable
fun VLVerticalGrid(dataList: List<ContentData>,
                   layout: Layout?,
//                   listener: TrayActionListener
){

    LazyVerticalGrid(columns = GridCells.Fixed(1), content = {
        items(dataList.size){ index ->
            HighlightTray01ViewItem(
                data = dataList[index],
                layout = layout,
//                listener = listener
            )
        }
    },
        modifier = Modifier
            .fillMaxWidth()
            .testTag("VERTICALGRID")
            .height(TrayUtil.gridHeight(layout?.settings, dataList.size))
        //.height(500.dp)
    )

}

@Composable
fun HighlightTray01ViewItem(
    data: ContentData,
    layout: Layout?,
) {
    val settings = layout?.settings
    when (settings?.thumbnailPlacement) {
        "top" -> {
            HighlightTopImageTrayItem(data, layout, false)
        }
        "left" -> {
            HighlightLeftImageTrayItem(data, layout, false)
        }
        "right" -> {
            HighlightRightImageTrayItem(data, layout, false)
        }
        else -> {
            HighlightLeftImageTrayItem(data, layout, false)
        }
    }
}


@Composable
fun HighlightLeftImageTrayItem(
    data: ContentData,
    layout: Layout?,
//    listener: TrayActionListener,
    isShowDescription: Boolean
) {
    var placeholder: MemoryCache.Key? = null
    var thumbType = layout?.settings?.thumbnailType ?: "landscape"
    // thumbType = "32*9"
    val imageSize = TrayUtil.trayVerticalImageSize(thumbType)
    val imageUrl = TrayUtil.trayImage(thumbType, data.gist?.imageGist!!, imageSize)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            //.background(TrayUtil.TryaBgColor(settings),shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        //colors = CardDefaults.cardColors(Color.Transparent)
        backgroundColor = TrayUtil.TryaBgColor(layout?.settings)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            val (thumbImage, iconType, titleText, itemInfo, shareAction) = createRefs()
            val endBarrier = createStartBarrier(shareAction)
            val topBarrier = createTopBarrier(iconType, itemInfo)

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
//                placeholder = painterResource(id = TrayUtil.placeholderMap.get(thumbType)!!),
//                error = painterResource(id = TrayUtil.placeholderMap.get(thumbType)!!),
                onSuccess = { placeholder = it.result.memoryCacheKey },
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .testTag("LEFTTHUMBNAIL")
                    .constrainAs(thumbImage) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        //height = Dimension.fillToConstraints
                        //end.linkTo(parent.end)

                    }
                    //.aspectRatio(16f/9f)
                    //.width(imageSize.width)
                    .size(imageSize)
                    .clip(RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp))
            )
            Text(
                text = data.gist?.title!!,
                style = TrayUtil.textStyle(layout, "trayItemTitle"),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    // .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    .constrainAs(titleText) {
                        start.linkTo(thumbImage.end, 12.dp)
                        top.linkTo(thumbImage.top, 12.dp)
                        bottom.linkTo(topBarrier)
                        //end.linkTo(parent.end)
                        end.linkTo(endBarrier, 12.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.White

            )
            /*Image(
                painter = painterResource(id = R.drawable.ic_article),
                contentDescription = null,
                modifier = Modifier.constrainAs(iconType) {
                    start.linkTo(titleText.start)
                    bottom.linkTo(parent.bottom, 12.dp)
                }
            )*/
            val publishedDate = if(data.publishDate!=null){
                UiUtils.getPublishDate(data.publishDate)
            } else {
                data.readTime
            }

            val info = TrayUtil.getAuthorName(publishedDate, data.author)
            Text(
                text = info,
                style = TrayUtil.textStyle(layout,"trayItemTitle").copy(
                    fontSize = 12.sp,
                    color = Color(0xff838996)
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    // .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    .constrainAs(itemInfo) {
                        //start.linkTo(iconType.end, 8.dp)
                        //bottom.linkTo(iconType.bottom)


                        end.linkTo(parent.end, 12.dp)
                        start.linkTo(titleText.start)
                        bottom.linkTo(parent.bottom, 12.dp)

                        width = Dimension.fillToConstraints
                    },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (layout?.settings?.enableSharing == true) {

                Image(
                    painter = painterResource(id = com.example.topstories.R.drawable.baseline_share_24),
                    contentDescription = null,
                    modifier = Modifier
                        .testTag("SHAREICON")
                        .constrainAs(shareAction) {
                            top.linkTo(titleText.top)
                            end.linkTo(parent.end, 12.dp)
                            start.linkTo(titleText.end, 20.dp)
                        }
                        .clickable {
//                            listener.shareFromTrayAction(data.gist.permalink!!)
                        }
                )
            }


        }
    }

}


@Composable
fun HighlightRightImageTrayItem(
    data: ContentData,
    layout: Layout?,
//    listener: TrayActionListener,
    isShowDescription: Boolean
) {
    var placeholder: MemoryCache.Key? = null
    var thumbType = layout?.settings?.thumbnailType ?: "landscape"
    // thumbType = "32*9"
    val imageSize = TrayUtil.trayVerticalImageSize(thumbType)
    val imageUrl = TrayUtil.trayImage(thumbType, data.gist?.imageGist!!, imageSize)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            //.background(TrayUtil.TryaBgColor(settings),shape)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        backgroundColor = TrayUtil.TryaBgColor(layout?.settings)
//        colors = CardDefaults.cardColors(TrayUtil.TryaBgColor(layout?.settings))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            val (thumbImage, iconType, titleText, itemInfo, shareAction) = createRefs()
            val endBarrier = createStartBarrier(shareAction,thumbImage)
            val topBarrier = createTopBarrier(iconType, itemInfo)

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
//                placeholder = painterResource(id = TrayUtil.placeholderMap.get(thumbType)!!),
//                error = painterResource(id = TrayUtil.placeholderMap.get(thumbType)!!),
                onSuccess = { placeholder = it.result.memoryCacheKey },
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .testTag("RIGHTTHUMBNAIL")
                    .constrainAs(thumbImage) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        //height = Dimension.fillToConstraints
                        //end.linkTo(parent.end)

                    }
                    //.aspectRatio(16f/9f)
                    //.width(imageSize.width)
                    .size(imageSize)
                    .clip(RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp))
            )
            Text(
                text = data.gist?.title!!,
                style = TrayUtil.textStyle(layout, "trayItemTitle"),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    // .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    .constrainAs(titleText) {
                        start.linkTo(parent.start, 12.dp)
                        top.linkTo(thumbImage.top, 12.dp)
                        bottom.linkTo(topBarrier)
                        //end.linkTo(parent.end)
                        end.linkTo(endBarrier, 12.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
            /*Image(
                painter = painterResource(id = R.drawable.ic_article),
                contentDescription = null,
                modifier = Modifier.constrainAs(iconType) {
                    start.linkTo(titleText.start)
                    bottom.linkTo(parent.bottom, 12.dp)
                }
            )*/
            val publishedDate = if(data.publishDate!=null){
                "${UiUtils.getPublishDate(data.publishDate)}h"
            } else {
                data.readTime
            }

            val info = TrayUtil.getAuthorName(publishedDate, data.author)
            Text(
                text = info,
                style = TrayUtil.textStyle(layout,"trayItemTitle").copy(
                    fontSize = 12.sp,
                    color = Color(0xff838996)
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    // .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                    .constrainAs(itemInfo) {
                        //start.linkTo(iconType.end, 8.dp)
                        //bottom.linkTo(iconType.bottom)


                        end.linkTo(thumbImage.start, 12.dp)
                        start.linkTo(titleText.start)
                        bottom.linkTo(parent.bottom, 12.dp)

                        width = Dimension.fillToConstraints
                    },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (layout?.settings?.enableSharing == true) {

                Image(
                    painter = painterResource(id = com.example.topstories.R.drawable.baseline_share_24),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(shareAction) {
                            top.linkTo(titleText.top)
                            end.linkTo(thumbImage.start, 12.dp)
                            start.linkTo(titleText.end, 20.dp)
                        }
                        .clickable {
//                            listener.shareFromTrayAction(data.gist.permalink!!)
                        }
                )
            }


        }
    }

}
@Composable
fun TrayParallaxImage(imageUrl: String?,
//                      resPlaceholder : Int,
                      modifier : Modifier){
    var placeholder: MemoryCache.Key? = null
    val imageDpSize = DpSize(275.dp, 244.dp)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .build(),
        onSuccess = { placeholder = it.result.memoryCacheKey },
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = modifier.fillMaxSize()
    )
}


@Composable
fun VLTray01(module: Module) {
    Column(modifier = Modifier.fillMaxWidth()) {

        VLTray01Header(
            title = module.title ?: "",
            subtitle = module.subtitle,
            layout = module.layout,
            trayTitleColor = Color.Black,
            isDivider = false,
//            listener = listener
        )

//            LazyRow(contentPadding = PaddingValues(start = 4.dp, end = 4.dp, top = 16.dp, bottom = 4.dp)) {
//                items(items = module.contentData,
//                    itemContent = {
//                        VLTray01ViewItem(
//                            data = it,
//                            layout = module.layout,
////                            listener = listener
//                        )
//                    }
//                )
//            }
//
//            if (module.layout?.settings?.showMore == true) {
//                VLSeeMoreButton(
//                    modifier = Modifier
//                    .padding(start = 16.dp, end = 16.dp, top = 28.dp, bottom = 0.dp)
//                    .fillMaxWidth(),module)
//            }
    }
}

@Composable
fun VLSeeMoreButton(modifier: Modifier, module: Module) {

    Button(
        onClick = { /*TODO*/ },
//        colors = ButtonDefaults.buttonColors(
////            containerColor = Color.Transparent,
////            contentColor = contentColorFor(backgroundColor = TrayUtil.trayBgColor)
//        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor = Color.White),
        modifier = modifier
            .testTag("SEEMOREBTN")
            .border(
                2.dp,
                //Color(0x75000000 + borderColor.toArgb()),
                //Color(0x25000000 +TrayUtil.TryaBgColor(settings).toArgb()),
//            TrayUtil.TryaButtonBorderColor(module.layout?.settings),
                color = TrayUtil.trayBgColor,
                //borderColor,
                RoundedCornerShape(4.dp)
            )
    ) {
        val seeMoreText = if (module.metadataMap?.get("seeMoreTxt") != null) {
            module.metadataMap["seeMoreTxt"]
        } else {
            TrayUtil.seeMoreCTA
        }
        Text(
            text = seeMoreText.toString()!!,
            style = TrayUtil.TypoGraphyMap["trayTitle"]!!.copy(fontSize = 14.sp)
        )
    }
}