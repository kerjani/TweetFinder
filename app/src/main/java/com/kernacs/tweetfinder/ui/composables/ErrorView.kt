package com.kernacs.tweetfinder.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kernacs.tweetfinder.R
import com.kernacs.tweetfinder.ui.theme.TweetFinderTheme

@Composable
fun ErrorView(errorMessage: String, tryAgainAction: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = errorMessage)
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = tryAgainAction
            ) {
                Text(stringResource(id = R.string.error_text))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    TweetFinderTheme {
        ErrorView(errorMessage = "Something bad happened. ") {

        }
    }
}