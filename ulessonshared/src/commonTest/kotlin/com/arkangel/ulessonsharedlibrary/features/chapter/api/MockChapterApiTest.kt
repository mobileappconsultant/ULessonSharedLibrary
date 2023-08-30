package com.arkangel.ulessonsharedlibrary.features.chapter.api

import com.arkangel.ulessonsharedlibrary.network.MockApiResponse
import com.arkangel.ulessonsharedlibrary.network.NativeClientMockEngine.Companion.getChapterApiMockEngine
import com.arkangel.ulessonsharedlibrary.network.NativeClientMockEngine.Companion.getHttpClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MockChapterApiTest {

    @Test
    fun `test getChapters`() = runTest{
        val mockChapterApi = MockChapterApi(getHttpClient(getChapterApiMockEngine()))

        val getChapters = mockChapterApi.getChapters("biology")

        assertEquals(getChapters, MockApiResponse.getChapter())
    }
}