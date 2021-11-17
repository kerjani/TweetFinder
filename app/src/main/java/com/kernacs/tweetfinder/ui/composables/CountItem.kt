package com.kernacs.tweetfinder.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kernacs.tweetfinder.ui.theme.TweetFinderTheme

@Composable
fun CountItem(count: Int, icon: ImageVector, secondaryTextColor: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = secondaryTextColor,
            modifier = Modifier
                .scale(0.65F)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.bodySmall.copy(color = secondaryTextColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountItemPreview() {
    TweetFinderTheme {
        CountItem(
            count = 1,
            icon = Icons.Outlined.Favorite,
            secondaryTextColor = MaterialTheme.colorScheme.error
        )
    }
}