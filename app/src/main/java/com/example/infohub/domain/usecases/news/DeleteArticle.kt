package com.example.infohub.domain.usecases.news

import com.example.infohub.domain.model.Article
import com.example.infohub.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {


    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}