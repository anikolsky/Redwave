package com.omtorney.redwave.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.omtorney.redwave.domain.model.Reply
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CommentCard(
    modifier: Modifier = Modifier,
    reply: Reply
) {
    val sdf = SimpleDateFormat("HH:mm  •  dd.MM.yyyy", Locale.getDefault())
    val dateTimeCreated = sdf.format(reply.created)

    Column(modifier = modifier.padding(12.dp)) {
        val text = reply.content
        var quote = ""
        if (text.contains("</blockquote>")) {
            quote = text.substring(0, text.indexOf("</blockquote>"))
            Text(text = quote, color = Color.Gray)
        }
        SelectionContainer {
            Text(
                text = text
                    .replace(quote, "")
                    .replace("</blockquote>", ""),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${reply.likes} likes",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "$dateTimeCreated  •  ${reply.author}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
            )
        }
        Text(text = "Number of replies: ${reply.replies.size}")
        if (reply.replies.isNotEmpty()) {
//            val replies: List<Reply> = reply.replies.filterIsInstance<Reply>()
            LazyColumn(modifier = Modifier.padding(start = 8.dp)) {
                items(reply.replies) { reply ->
                    Text(text = reply.content)
                }
            }
        }
//        if (reply.replies is List<*>) {
//            val replies: List<Reply> = reply.replies.filterIsInstance<Reply>()
//            LazyColumn(modifier = Modifier.padding(start = 8.dp)) {
//                replies.map { reply ->
//                    item {
//                        Text(text = reply.content)
//                    }
//                }
//            }
//        }
    }
}
