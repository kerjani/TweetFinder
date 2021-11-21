package com.kernacs.tweetfinder.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kernacs.tweetfinder.data.local.entities.TweetEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResultList(searchResultItems: List<TweetEntity>) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 1.dp)
    ) {
        items(
            items = searchResultItems,
            itemContent = { TweetListItem(tweet = it, modifier = Modifier.animateItemPlacement()) }
        )
    }

}