package com.omtorney.redwave.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.presentation.home.Subreddits
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun EntryCard(
    modifier: Modifier = Modifier,
    post: Post,
    onClick: (Post) -> Unit
) {
    val sdf = SimpleDateFormat("HH:mm  •  dd.MM.yyyy", Locale.getDefault())
    val dateTimeCreated = sdf.format(post.created)

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            if (post.isNew) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            } else {
                MaterialTheme.colorScheme.background
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onClick(post) }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${post.upVotes} rating  •  ${post.comments} replies",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "$dateTimeCreated  •  ${post.author}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = when (post.subreddit) {
                            Subreddits.AndroidDev.title -> Subreddits.AndroidDev.color.copy(alpha = 0.5f)
                            Subreddits.Coding.title -> Subreddits.Coding.color.copy(alpha = 0.5f)
                            else -> Subreddits.Kotlin.color.copy(alpha = 0.5f)
                        },
                        shape = MaterialTheme.shapes.medium
                    )
                    .align(Alignment.End)
            ) {
                Text(
                    text = post.subreddit,
                    style = MaterialTheme.typography.bodySmall,
                    color = when (post.subreddit) {
                        Subreddits.AndroidDev.title -> Subreddits.AndroidDev.color.copy(alpha = 0.7f)
                        Subreddits.Coding.title -> Subreddits.Coding.color.copy(alpha = 0.7f)
                        else -> Subreddits.Kotlin.color.copy(alpha = 0.7f)
                    },
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp)
                )
            }
        }
    }
}
