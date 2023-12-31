package com.arkangel.ulessonsharedlibrary.features.chapter.model
import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val title: String,
    val lessons: List<Lesson>
)
