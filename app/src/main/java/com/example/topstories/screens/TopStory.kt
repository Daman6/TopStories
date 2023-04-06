package com.example.topstories.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.viewlift.uimodule.data.ContentData
import com.viewlift.uimodule.data.Layout
import com.viewlift.uimodule.tray.util.TrayUtil


@Composable
fun TraySimpleHeader(
    title: String,
    trayTitleColor: Color,
    isDivider: Boolean,
    modifier: Modifier = Modifier
) {
   ConstraintLayout(
       modifier = modifier
           .fillMaxWidth()
           .padding(horizontal = 0.dp)
   ){
       val (divider, trayTitle) = createRefs()
       Text(
           text = title,
           style = MaterialTheme.typography.caption,
           color = trayTitleColor,
           modifier = Modifier
               .constrainAs(trayTitle) {
                   linkTo(
                       start = parent.start,
                       startMargin = 8.dp,
                       end = parent.end,
                       endMargin = 16.dp,
                       bias = 0f
                   )
               }
               .padding(horizontal = 8.dp, vertical = 8.dp),
           maxLines = 1,
           overflow = TextOverflow.Ellipsis
       )
       if (isDivider) {
           TrayDivider(Modifier.constrainAs(divider) {
               linkTo(start = parent.start, end = parent.end)
               bottom.linkTo(parent.bottom)
           })
       }

   }
}


@Composable
fun VLTray01Header(
    title: String,
    subtitle: String?,
    layout: Layout?,
    trayTitleColor: Color,
    isDivider: Boolean,
//    listener: TrayActionListener,
    modifier: Modifier = Modifier
) {
    val localTitle = title.replace("\\n","\n")
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            //.clickable { onSnackClick(snack.id) }
            .padding(horizontal = 0.dp)
    ) {
        val (trayHeaderIcon, divider, textBox, trayTitle, traySubtitle) = createRefs()
        val subtitleBarrier = createBottomBarrier(traySubtitle)
        val endBarrier = createEndBarrier(trayHeaderIcon)
//        var placeholder: MemoryCache.Key? = null
        val imageUrl = layout?.settings?.trayIconUrl
        var textPadding = 16.dp

        if (imageUrl != null && imageUrl.isNotEmpty()) {
            textPadding = 12.dp
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                // .parameters(image.parameters)
                .build(),
                placeholder = ColorPainter(Color.Transparent),
                error = ColorPainter(Color.Red),
//                onSuccess = { placeholder = it.result.memoryCacheKey },
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .sizeIn(32.dp,32.dp,40.dp,40.dp)
                    .constrainAs(trayHeaderIcon) {
                        start.linkTo(parent.start, 16.dp)
                        top.linkTo(textBox.top)
                        bottom.linkTo(textBox.bottom)
                    })
        }

        Column(modifier = Modifier.constrainAs(textBox) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(endBarrier, textPadding)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }) {
            if(!subtitle.isNullOrEmpty()){
                Text(
                    text = subtitle,
                    style = TrayUtil.textStyle(layout,"traySubTitle"),
                    color = trayTitleColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = localTitle,
                style = TrayUtil.textStyle(layout,"trayTitle"),
                color = trayTitleColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.testTag("TITLETAG")
            )
        }
        if (isDivider) {
            TrayDivider(Modifier.constrainAs(divider) {
                linkTo(start = parent.start, end = parent.end)
                top.linkTo(parent.bottom)
            })
        }
    }
}


@Composable
fun TrayDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray.copy(alpha = DividerAlpha),
    thickness: Dp = 10.dp
) {
    Divider(
        modifier = modifier.fillMaxWidth(),
        color = color,
        thickness = thickness
    )
}

private const val DividerAlpha = 0.12f


///

@Composable
fun VLTray01ViewItem(
    data: ContentData,
    layout: Layout?,
//    listener: TrayActionListener
) {

    val settings = layout?.settings
    val trayType = settings?.thumbnailType!!


    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .width(400.dp)
        .background(TrayUtil.TryaBgColor(settings)),
        elevation = 5.dp,
        shape =  RoundedCornerShape(corner = CornerSize(4.dp)),
//        backgroundColor = Color.Red,
//         colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .clickable {
//                    listener.clickItemTrayAction(data.gist?.id + " -- " + data.gist?.title)
                }
        ) {
//            VLTray01Image(
//                data = data,
//                settings = settings,
//                listener = listener
//            )
            VLTray01ItemText(
                data = data,
                textWidth = 5.dp,
                layout = layout,
//                listener
            )

//            if (data.gist?.contentType.equals("Playlist", true)|| data.gist?.contentType?.contains("Playlist", true) ?:false ) {
//                VLTrayPlayListItemText(
//                    data = data,
//                    trayType = trayType,
//                    settings = settings
//                )
//            }
        }
    }
}


@Composable
fun VLTray01ItemText(
    data: ContentData,
    textWidth: Dp,
    layout: Layout?,
//    listener: TrayActionListener
) {
    ConstraintLayout(
        modifier = Modifier
            .width(textWidth)
            .padding(12.dp)
    ) {
        val (titleText, shareAction) = createRefs()
        val endBarrier = createStartBarrier(shareAction)

        Text(
            text = data.gist?.title!!,
            style = TrayUtil.textStyle(layout,"trayItemTitle"),
            textAlign = TextAlign.Start,
            modifier = Modifier
                 .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
                .defaultMinSize(minHeight = 38.dp)
                .constrainAs(titleText) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    end.linkTo(endBarrier, 0.dp)
                    width = Dimension.fillToConstraints
                },
            maxLines = 2,

            overflow = TextOverflow.Ellipsis
        )
        if (layout?.settings?.enableSharing == true) {

                Image(
                    painter = painterResource(id = com.example.topstories.R.drawable.baseline_share_24),
                    contentDescription = null,
                    modifier = Modifier
                        .testTag("TOPSTORYMAINSHAREICON")
                        .constrainAs(shareAction) {
                            top.linkTo(titleText.top)
                            end.linkTo(parent.end)
                            start.linkTo(titleText.end, 20.dp)
                        }
                        .clickable {
//                        listener.shareFromTrayAction(data.gist?.permalink!!)
                        }
                ) /*{

                Image(painter = painterResource(id = R.drawable.icon_share), contentDescription = null )
            }*/
        }
    }

}
