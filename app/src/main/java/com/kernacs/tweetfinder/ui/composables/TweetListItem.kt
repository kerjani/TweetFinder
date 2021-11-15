package com.kernacs.tweetfinder.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.kernacs.tweetfinder.data.remote.dto.TweetSearchDto

@Composable
fun TweetListItem(item: TweetSearchDto.Tweet) {
    Card(
        modifier = Modifier.padding(4.dp),
        backgroundColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        val primaryTextColor = MaterialTheme.colorScheme.onSurface
        val secondaryTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        Row {
            ProfilePicture(item.user.profileImageUrlHttps)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            )
            {
                Column {
                    Text(
                        text = item.user.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = primaryTextColor,
                        modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 0.dp)
                    )
                    Text(
                        text = "@${item.user.screenName}",
                        style = MaterialTheme.typography.labelMedium,
                        color = secondaryTextColor,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                Text(
                    text = item.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = primaryTextColor,
                    modifier = Modifier.padding(0.dp, 10.dp, 15.dp, 17.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(horizontalArrangement = Arrangement.End) {
                        Icon(
                            Icons.Outlined.MailOutline,
                            "",
                            tint = secondaryTextColor,
                            modifier = Modifier
                                .scale(0.65F)
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            if (item.retweeted) Icons.Filled.Refresh else Icons.Outlined.Refresh,
                            "",
                            tint = secondaryTextColor,
                            modifier = Modifier
                                .scale(0.65F)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = item.retweetCount.toString(),
                            style = MaterialTheme.typography.bodySmall.copy(color = secondaryTextColor)
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            if (item.favorited) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                            "",
                            tint = secondaryTextColor,
                            modifier = Modifier
                                .scale(0.65F)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = item.favoriteCount.toString(),
                            style = MaterialTheme.typography.bodySmall.copy(color = secondaryTextColor)
                        )
                    }
                    Row {
                        Icon(
                            Icons.Outlined.Share,
                            "",
                            tint = secondaryTextColor,
                            modifier = Modifier
                                .scale(0.65F)
                        )
                    }
                }
            }
        }
    }
}