package com.omtorney.redwave.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.omtorney.redwave.domain.model.Post

@Composable
fun EntryCard(
    modifier: Modifier = Modifier,
    post: Post,
    onClick: (Post) -> Unit
) {
    Card(
        shape = RoundedCornerShape(0.dp),
        backgroundColor = if (post.isNew) {
            MaterialTheme.colors.primary.copy(alpha = 0.1f)
        } else {
            MaterialTheme.colors.background
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 0.dp)
            .clickable { onClick(post) }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Comments: ${post.comments}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.6f)
                )
                Row {
                    Text(
                        text = post.upVotes.toString(),
                        style = MaterialTheme.typography.body2,
                        color = Color.Green.copy(alpha = 0.3f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = post.downVotes.toString(),
                        style = MaterialTheme.typography.body2,
                        color = Color.Red.copy(alpha = 0.3f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = post.author,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.3f)
                )
                Text(
                    text = post.created,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.3f)
                )
            }
        }
    }
    Divider()
}