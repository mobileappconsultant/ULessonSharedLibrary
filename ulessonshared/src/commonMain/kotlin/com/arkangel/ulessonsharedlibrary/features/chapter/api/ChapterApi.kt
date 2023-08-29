package com.arkangel.ulessonsharedlibrary.features.chapter.api

import com.arkangel.ulessonsharedlibrary.features.chapter.model.Chapter

interface ChapterApi {
    suspend fun getChapters(subject: String): List<Chapter>
}
