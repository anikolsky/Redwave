package com.omtorney.redwave.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omtorney.redwave.R
import com.omtorney.redwave.domain.model.Post
import com.omtorney.redwave.presentation.common.EntryCard
import com.omtorney.redwave.presentation.common.Spinner

@Composable
fun HomeScreen(
    onEntryClick: (Post, String) -> Unit
) {
    val viewModel = hiltViewModel<HomeViewModel>() // TODO move to NavHost
    val state = viewModel.state
    val selectedSubreddit = viewModel.selectedSubreddit
    var selectedSortType by rememberSaveable { mutableStateOf(Sort.New.type) }

    Scaffold(
        topBar = {},
        bottomBar = {
            BottomAppBar(
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = viewModel::clearCache,
                        elevation = FloatingActionButtonDefaults.elevation(0.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_delete),
                            contentDescription = "Clear cache"
                        )
                    }
                }
            )
        }
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
                    onClick = { viewModel.getEntries(selectedSubreddit) },
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary),
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
            .fillMaxWidth()
            .height(45.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
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
