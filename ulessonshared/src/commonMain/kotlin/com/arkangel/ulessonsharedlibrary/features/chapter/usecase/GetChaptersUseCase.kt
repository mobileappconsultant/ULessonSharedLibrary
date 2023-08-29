package com.arkangel.ulessonsharedlibrary.features.chapter.usecase

import com.arkangel.ulessonsharedlibrary.di.sharedDI
import com.arkangel.ulessonsharedlibrary.features.chapter.model.Chapter
import com.arkangel.ulessonsharedlibrary.features.chapter.repository.ChapterRepository
import com.arkangel.ulessonsharedlibrary.utils.ApiResponse

class GetChaptersUseCase {
    private val chapterRepository: ChapterRepository by sharedDI.koin.inject()

    suspend fun getChapters(subject: String): ApiResponse<List<Chapter>> {
        return chapterRepository.getChapters(subject)
    }
}
