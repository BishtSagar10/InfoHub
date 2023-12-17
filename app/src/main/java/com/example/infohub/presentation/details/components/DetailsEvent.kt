package com.example.infohub.presentation.details.components

import com.example.infohub.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article):DetailsEvent()

    object RemoveSideEffect:DetailsEvent()
}