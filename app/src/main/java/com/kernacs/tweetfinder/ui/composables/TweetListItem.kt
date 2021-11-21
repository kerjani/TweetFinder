package com.kernacs.tweetfinder.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kernacs.tweetfinder.data.local.entities.TweetEntity
import com.kernacs.tweetfinder.util.formatTweetDate

@Composable
fun TweetListItem(tweet: TweetEntity) {
    Card(
        modifier = Modifier
            //.animateItemPlacement() TODO uncomment when it will be released https://twitter.com/CatalinGhita4/status/1455500904690552836?s=20
            .padding(4.dp),
        backgroundColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        val primaryTextColor = MaterialTheme.colorScheme.onSurface
        val secondaryTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        Row {
            ProfilePicture(tweet.authorProfileImageUrl)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Column {
                    Text(
                        text = tweet.authorName,
                        style = MaterialTheme.typography.titleMedium,
                        color = primaryTextColor,
                        modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 0.dp)
                    )
                    Text(
                        text = "@${tweet.authorScreenName}",
                        style = MaterialTheme.typography.labelMedium,
                        color = secondaryTextColor,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                Text(
                    text = tweet.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = primaryTextColor,
                    modifier = Modifier.padding(0.dp, 10.dp, 15.dp, 17.dp)
                )
                Text(
                    text = tweet.createdAt.formatTweetDate(),
                    style = MaterialTheme.typography.bodySmall,
                    color = primaryTextColor,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CountItem(tweet.quoteCount, Icons.Outlined.MailOutline, secondaryTextColor)
                    CountItem(tweet.likeCount, Icons.Outlined.Favorite, secondaryTextColor)
                    CountItem(tweet.replyCount, Icons.Outlined.Refresh, secondaryTextColor)
                    CountItem(tweet.retweetCount, Icons.Outlined.Share, secondaryTextColor)
                }
            }
        }
    }
}