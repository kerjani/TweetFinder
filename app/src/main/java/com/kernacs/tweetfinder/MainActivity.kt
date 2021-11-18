package com.kernacs.tweetfinder

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Snackbar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
                    val searchResultItems =
                        searchResultViewModel.searchResult.collectAsState(initial = emptyList())
                    when (val state = viewState.value) {
                        is TweetSearchViewModel.ViewState.SearchResult -> {

                            if (searchResultItems.value.isEmpty()) {
                                EmptyView {
                                    // TODO focus on the search view, bring the keyboard up
                                }
                            } else {
                                SearchResultList(searchResultItems = searchResultItems.value)
                            }
                        }
                        is TweetSearchViewModel.ViewState.Error -> {
                            val errorMessage = state.exception?.localizedMessage
                                ?: getString(R.string.generic_error_message)
                            val actionText = stringResource(id = R.string.error_text)
                            val errorAction = {
                                // TODO re-run search with the same term
                            }
                            if (searchResultItems.value.isEmpty()) {
                                ErrorView(errorMessage, actionText, errorAction)
                            } else {
                                Log.d(
                                    TAG,
                                    "Showing error in snack bar while showing the offline data as well"
                                )

                                SearchResultList(searchResultItems = searchResultItems.value)
                                Snackbar(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(Alignment.Bottom),
                                    content = {
                                        Text(
                                            errorMessage,
                                            style = MaterialTheme.typography.labelMedium
                                                .copy(color = MaterialTheme.colorScheme.onPrimary)
                                        )
                                    })
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

    companion object {
        const val TAG = "MainActivity"
    }
}