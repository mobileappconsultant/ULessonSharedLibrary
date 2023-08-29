package com.arkangel.ulessonsharedlibrary.features.bookmark

import com.arkangel.ulessonsharedlibrary.features.bookmark.model.Bookmark
import com.arkangel.ulessonsharedlibrary.features.chapter.model.Lesson

interface BookmarkManager {
    fun getBookmarks(lesson: Lesson): List<Bookmark>
    fun addBookmark(lesson: Lesson, bookmark: Bookmark)
    fun removeBookmark(lesson: Lesson, bookmark: Bookmark)
}
