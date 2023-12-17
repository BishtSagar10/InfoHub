package com.example.infohub.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.infohub.domain.model.Article
import com.example.infohub.presentation.common.ArticlesList
import com.example.infohub.presentation.common.SearchBar
import com.example.infohub.presentation.onboarding.dimens.MediumPadding1

@Composable
fun SearchScreen(
    state:SearchState,
    event:(SearchEvent)->Unit,
    navigateToDetails:(Article)->Unit
){
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {event(SearchEvent.SearchNews)})


        Spacer(modifier=Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles=it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = {navigateToDetails(it)})
        }

    }

}