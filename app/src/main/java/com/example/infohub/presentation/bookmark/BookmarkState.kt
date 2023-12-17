package com.example.infohub.presentation.bookmark

import com.example.infohub.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
) {
}