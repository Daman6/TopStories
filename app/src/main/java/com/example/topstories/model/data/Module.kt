package com.example.topstories.model.data

data class Module(
    val contentData: List<ContentData>,
    val id: String,
    val layout: Layout,
    val metadataMap: MetadataMap,
    val moduleType: String,
    val subtitle: String,
    val title: String
)