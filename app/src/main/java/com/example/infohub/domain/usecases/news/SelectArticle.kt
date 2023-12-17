package com.example.infohub.domain.usecases.news

import com.example.infohub.domain.model.Article
import com.example.infohub.domain.repository.NewsRepository

class SelectArticle (
    private val newsRepository: NewsRepository
) {


    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }
}
