package com.omtorney.redwave.presentation.home

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
import com.omtorney.redwave.data.model.Entry
import com.omtorney.redwave.presentation.common.EntryCard
import com.omtorney.redwave.presentation.common.Sort
import com.omtorney.redwave.presentation.common.Spinner
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    onEntryClick: (Entry, String) -> Unit
) {
    val viewModel = getViewModel<HomeViewModel>()
    val state = viewModel.state
    var selectedSubreddit by rememberSaveable { mutableStateOf("Kotlin") }
    var selectedSortType by rememberSaveable { mutableStateOf(Sort.NEW.type) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    MySpinner(
                        items = listOf("Android", "coding", "Kotlin"),
                        selectedItem = selectedSubreddit,
                        onItemSelected = { selectedSubreddit = it },
                        modifier = Modifier.weight(3f)
                    )
                    MySpinner(
                        items = listOf(Sort.HOT.type, Sort.NEW.type, Sort.TOP.type),
                        selectedItem = selectedSortType,
                        onItemSelected = { selectedSortType = it },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            state.feed.entries.map { entry ->
                item {
                    EntryCard(
                        entry = entry,
                        onClick = { onEntryClick(it, selectedSortType) }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
        Button(
            onClick = { viewModel.loadFeed(selectedSubreddit, selectedSortType) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Load posts")
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

@Composable
fun MySpinner(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
) {
    Spinner(
        dropDownModifier = Modifier.wrapContentSize(),
        items = items,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected,
        selectedItemFactory = { modifier, item ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
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
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.small
            )
    )
}