package com.kernacs.tweetfinder.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kernacs.tweetfinder.R
import com.kernacs.tweetfinder.ui.theme.TweetFinderTheme

@Composable
fun WaitForStreamView(onContinueClicked: () -> Unit) = InfoView(
    stringResource(R.string.wait_info_text),
    stringResource(R.string.wait_button_text),
    onContinueClicked
)

@Preview(showBackground = true)
@Composable
private fun WaitPreview() {
    TweetFinderTheme { WaitForStreamView {} }
}


@Composable
fun EmptyView(onContinueClicked: () -> Unit) = InfoView(
    stringResource(id = R.string.empty_text),
    stringResource(id = R.string.empty_action),
    onContinueClicked
)

@Preview(showBackground = true)
@Composable
private fun EmptyPreview() {
    TweetFinderTheme { EmptyView {} }
}


@Composable
fun OnBoardingView(onContinueClicked: () -> Unit) = InfoView(
    stringResource(id = R.string.onboarding_message, stringResource(id = R.string.app_name)),
    stringResource(R.string.onboarding_button_text),
    onContinueClicked
)

@Preview(showBackground = true)
@Composable
private fun OnBoardingPreview() {
    TweetFinderTheme { OnBoardingView {} }
}


@Composable
fun ErrorView(
    errorMessage: String,
    buttonText: String = stringResource(id = R.string.error_text),
    tryAgainAction: () -> Unit
) = InfoView(
    errorMessage,
    buttonText, tryAgainAction
)

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    TweetFinderTheme {
        ErrorView(errorMessage = "Something bad happened. ") {}
    }
}