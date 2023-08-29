package com.arkangel.ulessonsharedlibrary.features.chapter.repository

import com.arkangel.ulessonsharedlibrary.features.chapter.model.Chapter
import com.arkangel.ulessonsharedlibrary.utils.ApiResponse

interface ChapterRepository {
    suspend fun getChapters(subject: String): ApiResponse<List<Chapter>>
}
