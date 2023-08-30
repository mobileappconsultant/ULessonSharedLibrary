package com.arkangel.ulessonsharedlibrary.utils

import com.arkangel.ulessonsharedlibrary.features.bookmark.model.Bookmark
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiResponseTest {

    @Test
    fun `test bookmark serialization`() {
        // Given
        val bookmark = Bookmark("Important note", 1234567890L)

        // When
        val jsonString = Json.encodeToString(Bookmark.serializer(), bookmark)
        val parsedBookmark = Json.decodeFromString(Bookmark.serializer(), jsonString)

        // Then
        assertEquals(bookmark, parsedBookmark)
    }
}