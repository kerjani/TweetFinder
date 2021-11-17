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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kernacs.tweetfinder.R
import com.kernacs.tweetfinder.ui.theme.TweetFinderTheme

@Composable
fun InfoView(infoText: String, buttonText: String, buttonAction: (() -> Unit)? = null) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                infoText,
                Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
            buttonAction?.let { action ->
                Button(
                    modifier = Modifier.padding(vertical = 12.dp),
                    onClick = action
                ) {
                    Text(buttonText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WithButtonPreview() {
    TweetFinderTheme {
        InfoView(
            stringResource(id = R.string.empty_text),
            stringResource(id = R.string.empty_action)
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun WithoutButtonPreview() {
    TweetFinderTheme {
        InfoView(
            stringResource(id = R.string.empty_text),
            stringResource(id = R.string.empty_action)
        )
    }
}