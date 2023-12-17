package com.example.infohub.domain.usecases.news

import com.example.infohub.domain.model.Article
import com.example.infohub.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {


    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}