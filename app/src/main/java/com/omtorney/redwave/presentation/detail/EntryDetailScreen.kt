package com.omtorney.redwave.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EntryDetailScreen() {
    val viewModel = hiltViewModel<EntryDetailViewModel>()
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = state.postTitle,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Text(
                    text = state.postContent,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(4.dp)
                )
            }
            items(state.comments) { comment ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 2.dp, vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                        val text = comment.body
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
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = comment.upVotes.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Green.copy(alpha = 0.3f)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = comment.downVotes.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Red.copy(alpha = 0.3f)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = comment.author,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                            )
                            Text(
                                text = comment.created,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                            )
                        }
                    }
                }
//                if (comment.replies is List<*>) {
//                    val replies = comment.replies as List<Comment>
//                    replies.map {
//                        Text(text = it.body)
//                    }
//                    Row {
//                        Spacer(modifier = Modifier.width(8.dp))
//                        LazyColumn {
//                            item { comment.replies.first().toString() }
//                        }
//                    }
//                }
            }
        }
        if (state.isLoading) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Loading...")
            }
        }
        if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}