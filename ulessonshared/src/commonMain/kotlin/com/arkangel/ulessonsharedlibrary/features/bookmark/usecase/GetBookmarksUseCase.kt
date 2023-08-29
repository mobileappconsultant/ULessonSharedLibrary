package com.arkangel.ulessonsharedlibrary.features.bookmark.usecase

import com.arkangel.ulessonsharedlibrary.di.sharedDI
import com.arkangel.ulessonsharedlibrary.features.bookmark.BookmarkManager
import com.arkangel.ulessonsharedlibrary.features.bookmark.model.Bookmark
import com.arkangel.ulessonsharedlibrary.features.chapter.model.Lesson

class GetBookmarksUseCase {
    private val bookmarkManager: BookmarkManager by sharedDI.koin.inject()
    fun getBookmarks(lesson: Lesson): List<Bookmark> = bookmarkManager.getBookmarks(lesson)
}
