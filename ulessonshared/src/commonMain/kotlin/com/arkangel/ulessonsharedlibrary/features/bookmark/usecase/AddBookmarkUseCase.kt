package com.arkangel.ulessonsharedlibrary.features.bookmark.usecase

import com.arkangel.ulessonsharedlibrary.di.sharedDI
import com.arkangel.ulessonsharedlibrary.features.bookmark.BookmarkManager
import com.arkangel.ulessonsharedlibrary.features.bookmark.model.Bookmark
import com.arkangel.ulessonsharedlibrary.features.chapter.model.Lesson

class AddBookmarkUseCase {
    private val bookmarkManager: BookmarkManager by sharedDI.koin.inject()
    fun addBookmark(lesson: Lesson, bookmark: Bookmark) = bookmarkManager.addBookmark(lesson, bookmark)
}
