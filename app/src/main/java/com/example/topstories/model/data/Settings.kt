package com.example.topstories.model.data

data class Settings(
    val columns: Columns,
    val enableCustomStyle: Boolean,
    val enableOverrideSettings: Boolean,
    val enableSharing: Boolean,
    val loop: Boolean,
    val parallax: Parallax,
    val removeTrayBottomTitle: Boolean,
    val roundedCornerButton: Boolean,
    val seeAll: Boolean,
    val seeAllCard: Boolean,
    val showContentDuration: Boolean,
    val showMore: Boolean,
    val showMorePermalink: String,
    val textBackgroundColor: TextBackgroundColor,
    val thumbnailPlacement: String,
    val thumbnailType: String,
    val trayIconUrl: String
)