package com.kernacs.tweetfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.kernacs.tweetfinder.ui.composables.*
import com.kernacs.tweetfinder.ui.theme.TweetFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val searchResultViewModel by viewModels<TweetSearchViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetFinderTheme {

                val state = remember { mutableStateOf(TextFieldValue("")) }

                Scaffold(
                    topBar = { SearchView(state, searchResultViewModel::search) }
                ) {
                    val viewState = searchResultViewModel.viewState.observeAsState()
                    when (val state = viewState.value) {
                        is TweetSearchViewModel.ViewState.Data -> {
                            val searchResultItems = state.searchResult
                            if (searchResultItems.isEmpty()) {
                                EmptyView {
                                    // TODO focus on the search view, bring the keyboard up
                                }
                            } else {
                                SearchResultList(searchResultItems = state.searchResult)
                            }
                        }
                        is TweetSearchViewModel.ViewState.Error -> { // TODO error handling
                            ErrorView(
                                state.exception?.message
                                    ?: getString(R.string.generic_error_message)
                            ) {
                                // TODO re-run search with the same term
                            }
                        }
                        is TweetSearchViewModel.ViewState.Loading -> {
                            LoadingDialog()
                        }
                        is TweetSearchViewModel.ViewState.WaitingForStream -> {
                            WaitForStreamView {
                                // TODO focus on the search view, bring keyboard up
                            }
                        }
                        else -> {
                            OnBoardingView {
                                // TODO focus on the search view, bring keyboard up
                            }
                        }
                    }
                }
            }
        }
    }
}