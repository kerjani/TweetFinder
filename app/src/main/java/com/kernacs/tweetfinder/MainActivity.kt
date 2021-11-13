package com.kernacs.tweetfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kernacs.tweetfinder.R
import com.kernacs.tweetfinder.ui.theme.TweetFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenContent()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colorScheme.primary,
                contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Search, contentDescription = null)
                    }
                }
            )
        }
    ) {
        MainContent()
    }


}

@Composable
fun MainContent() {

    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnBoarding) {
        OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
    } else {
        SearchResult()
    }
}

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to Tweet Finder!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}


@Composable
fun SearchResult() {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            Tweet("Item #$it")
        }
    }

}

@Composable
fun Tweet(text: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hello $text!",
            modifier = Modifier
                .padding(start = 24.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TweetFinderTheme {
        ScreenContent()
    }
}