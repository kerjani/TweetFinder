package com.kernacs.tweetfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kernacs.tweetfinder.data.remote.dto.TweetDto
import com.kernacs.tweetfinder.ui.composables.EmptyView
import com.kernacs.tweetfinder.ui.composables.LoadingDialog
import com.kernacs.tweetfinder.ui.composables.OnBoardingView
import com.kernacs.tweetfinder.ui.composables.SearchResultList
import com.kernacs.tweetfinder.ui.theme.TweetFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val searchResultViewModel by viewModels<TweetSearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenContent(searchResultViewModel)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(viewModel: TweetSearchViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) {
        val searchResultItems by viewModel.searchResultItems.observeAsState(initial = null)
        val isLoading by viewModel.isLoading.observeAsState(initial = false)
        if (isLoading) {
            LoadingDialog()
        } else {
            MainContent(searchResultItems, viewModel::search)
        }
        viewModel.error
    }

}

@Composable
fun MainContent(searchResultItems: List<TweetDto>?, search: (String) -> Unit) {

    searchResultItems?.let {
        return if (it.isEmpty()) {
            EmptyView { search.invoke("nasa") }
        } else {
            SearchResultList(it)
        }
    }
        ?: OnBoardingView(onContinueClicked = { search.invoke("nasa") })
}