package com.arkangel.ulessonsharedlibrary.di

import com.arkangel.ulessonsharedlibrary.features.bookmark.BookmarkManager
import com.arkangel.ulessonsharedlibrary.features.bookmark.BookmarkManagerImpl
import com.arkangel.ulessonsharedlibrary.features.chapter.api.ChapterApi
import com.arkangel.ulessonsharedlibrary.features.chapter.api.MockChapterApi
import com.arkangel.ulessonsharedlibrary.features.chapter.repository.ChapterRepository
import com.arkangel.ulessonsharedlibrary.features.chapter.repository.ChapterRepositoryImpl
import com.arkangel.ulessonsharedlibrary.network.makeClient
import com.russhwolf.settings.Settings
import org.koin.core.KoinApplication
import org.koin.dsl.module

internal val sharedDI: KoinApplication by lazy {
    KoinApplication.init().apply {
        modules(
            module {
                single { makeClient() }
                single { Settings() }

                // Chapter Api
                factory<ChapterApi> { MockChapterApi(get()) }

                // Chapter Repository
                factory<ChapterRepository> { ChapterRepositoryImpl(get()) }

                // Bookmarks
                single<BookmarkManager> { BookmarkManagerImpl(get()) }
            }
        )
    }
}
