package com.angrypenguin.mikesinventorysystem.components.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angrypenguin.mikesinventorysystem.components.listitems.InventoryListItem
import com.angrypenguin.mikesinventorysystem.data.InventoryItem

@Composable
fun ListInventoryScreen(items: List<InventoryItem>, deleteItem: (item: InventoryItem) -> Unit) {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Inventory List:",
            style = TextStyle(
                fontSize = 18.sp
            )
        )
        LazyColumn() {
            items(items = items) { item ->
                InventoryListItem(item = item, deleteItem)
            }
        }
    }
}