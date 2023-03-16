package com.omtorney.redwave.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.omtorney.redwave.data.model.Entry

@Composable
fun EntryCard(
    entry: Entry,
    modifier: Modifier = Modifier,
    onClick: (Entry) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .clickable { onClick(entry) }
        ) {
            Text(
                text = entry.title.toString(),
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = entry.published.toString(),
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )
        }
    }
}