package com.omtorney.redwave.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omtorney.redwave.R
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.presentation.common.EntryCard
import com.omtorney.redwave.presentation.common.Spinner
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    onEntryClick: (Post, String) -> Unit
) {
    val viewModel = getViewModel<HomeViewModel>() // TODO move to NavHost
    val state = viewModel.state
    val selectedSubreddit = viewModel.selectedSubreddit
    var selectedSortType by rememberSaveable { mutableStateOf(Sort.New.type) }
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.clearCache()
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.round_delete),
                    contentDescription = "Clear cache"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                MySpinner(
                    items = listOf(
                        Subreddit.AndroidDev.title,
                        Subreddit.Coding.title,
                        Subreddit.Kotlin.title
                    ),
                    selectedItem = selectedSubreddit,
                    onItemSelected = { viewModel.selectSubreddit(it) },
                    modifier = Modifier.weight(2.5f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                MySpinner(
                    items = listOf(
                        Sort.Hot.type,
                        Sort.New.type,
                        Sort.Top.type
                    ),
                    selectedItem = selectedSortType,
                    onItemSelected = { selectedSortType = it },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                OutlinedButton(
                    onClick = { viewModel.getEntries(selectedSubreddit, selectedSortType) },
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_sync),
                        contentDescription = "Load",
                    )
                }
            }
            if (state.posts.isNotEmpty()) {
                LazyColumn(Modifier.fillMaxSize()) {
                    state.posts.map { post ->
                        item {
                            EntryCard(
                                post = post,
                                onClick = {
                                    onEntryClick(it, selectedSortType)
                                    if (post.isNew) {
                                        viewModel.markEntryAsRead(it)
                                    }
                                }
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
            }
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
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
            }
            if (state.error.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = state.error,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
//        if (state.entries.isEmpty()) {
//            Box(modifier = Modifier.fillMaxSize()) {
//                Text(
//                    text = "Refresh feed",
//                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            }
//        }
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
        dropDownModifier = modifier.wrapContentSize(),
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
                    style = MaterialTheme.typography.body1
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
                style = MaterialTheme.typography.body1
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small
            )
    )
}

enum class Subreddit(val title: String) {
    AndroidDev("androiddev"),
    Coding("coding"),
    Kotlin("Kotlin")
}

enum class Sort(val type: String) {
    Hot("hot"),
    New("new"),
    Top("top")
}