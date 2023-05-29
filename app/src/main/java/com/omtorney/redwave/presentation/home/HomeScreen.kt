package com.omtorney.redwave.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.omtorney.redwave.R
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.presentation.common.EntryCard
import com.omtorney.redwave.presentation.common.FeedEvent
import com.omtorney.redwave.presentation.common.FeedState
import com.omtorney.redwave.presentation.common.Spinner

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    state: FeedState,
    onEvent: (FeedEvent) -> Unit,
    onEntryClick: (Post, String) -> Unit
) {
    var selectedSubreddit by rememberSaveable { mutableStateOf(state.selectedSubreddit) }
    var selectedSortType by rememberSaveable { mutableStateOf(Sort.New.type) }

    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Redwave")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
//                    MySpinner(
//                        items = listOf(
//                            Subreddit.AndroidDev.title,
//                            Subreddit.Coding.title,
//                            Subreddit.Kotlin.title
//                        ),
//                        selectedItem = selectedSubreddit,
//                        onItemSelected = { item ->
//                            selectedSubreddit = item
//                            onEvent(FeedEvent.SelectSubreddit(selectedSubreddit))
//                        },
//                            modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth(0.5f)
//                    )
//                        Spacer(modifier = Modifier.width(4.dp))
//                        MySpinner(
//                            items = listOf(
//                                Sort.Hot.type,
//                                Sort.New.type,
//                                Sort.Top.type
//                            ),
//                            selectedItem = selectedSortType,
//                            onItemSelected = { selectedSortType = it },
//                            modifier = Modifier.weight(1f)
//                        )
                    IconButton(onClick = { onEvent(FeedEvent.MarkAllAsRead(selectedSubreddit)) }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Mark all as read"
                        )
                    }
                    IconButton(onClick = { onEvent(FeedEvent.ClearCache(selectedSubreddit)) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Clear cache"
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { onEvent(FeedEvent.GetEntries(selectedSubreddit)) },
                        elevation = FloatingActionButtonDefaults.elevation(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_sync),
                            contentDescription = "Load"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                state.posts.map { post ->
                    item {
                        EntryCard(
                            post = post,
                            onClick = {
                                onEntryClick(it, selectedSortType)
                            },
                            modifier = Modifier.animateItemPlacement()
                        )
                    }
                }
            }

            LaunchedEffect(state.posts.size) {
                listState.scrollToItem(0)
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            if (state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                )
            }
            if (state.posts.isEmpty() && !state.isLoading && state.error == null) {
                Text(
                    text = "Feed is empty",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun MySpinner(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
) {
    Spinner(
//        dropDownModifier = Modifier.wrapContentSize(),
        items = items,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected,
        selectedItemFactory = { modif, item ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modif
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.bodyLarge
                )
                Icon(
                    painter = painterResource(R.drawable.ic_round_arrow_down),
                    contentDescription = "Drop down"
                )
            }
        },
        dropdownItemFactory = { item, _ ->
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = modifier
//            .fillMaxWidth()
//            .height(45.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            )
    )
}

sealed class Subreddits(val title: String, val color: Color) {
    object AndroidDev : Subreddits("androiddev", Color(0xFF00DE7A))
    object Coding : Subreddits("coding", Color(0xFF6694DE))
    object Kotlin : Subreddits("Kotlin", Color(0xFF9640FA))
}

enum class Sort(val type: String) {
    Hot("hot"),
    New("new"),
    Top("top")
}
