package com.arkangel.ulessonsharedlibrary.network

import com.arkangel.ulessonsharedlibrary.features.chapter.model.Chapter
import com.arkangel.ulessonsharedlibrary.features.chapter.model.Lesson

internal class MockApiResponse {

    companion object {
        fun getChapter(): List<Chapter> {
            return List(10) { index ->
                Chapter(
                    title = "$index. title",
                    lessons = List(2) {
                        Lesson(
                            "Lesson $it",
                            "https://$it.com"
                        )
                    }
                )
            }
        }
    }
}