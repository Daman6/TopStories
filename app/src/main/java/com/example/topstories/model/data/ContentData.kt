package com.example.topstories.model.data

data class ContentData(
    val author: String,
    val awayTeam: AwayTeam,
    val contentType: String,
    val gist: GistX,
    val highlights: List<Any>,
    val homeTeam: HomeTeam,
    val id: String,
    val livestreams: List<Any>,
    val metadata: List<Any>,
    val preview: Preview,
    val publishDate: Int,
    val readTime: String,
    val runtime: Int,
    val schedules: List<Any>,
    val score: Score,
    val states: States
)