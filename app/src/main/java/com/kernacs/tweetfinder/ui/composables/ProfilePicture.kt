package com.kernacs.tweetfinder.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.kernacs.tweetfinder.ui.theme.TweetFinderTheme

@Composable
fun ProfilePicture(profileImageUrl: String) {
    Image(
        painter = rememberImagePainter(profileImageUrl),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(40.dp)
            .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
    )
}

@Preview(showBackground = true)
@Composable
fun ProfilePicturePreview() {
    TweetFinderTheme {
        ProfilePicture(profileImageUrl = "https://pbs.twimg.com/profile_images/188302352/nasalogo_twitter_normal.jpg")
    }
}