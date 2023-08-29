package com.arkangel.ulessonsharedlibrary.features.bookmark.usecase

import com.arkangel.ulessonsharedlibrary.di.sharedDI
import com.arkangel.ulessonsharedlibrary.features.bookmark.BookmarkManager
import com.arkangel.ulessonsharedlibrary.features.bookmark.model.Bookmark
import com.arkangel.ulessonsharedlibrary.features.chapter.model.Lesson

class DeleteBookmarkUseCase {
    private val bookmarkManager: BookmarkManager by sharedDI.koin.inject()
    fun deleteBookmark(lesson: Lesson, bookmark: Bookmark) = bookmarkManager.removeBookmark(lesson, bookmark)
}
