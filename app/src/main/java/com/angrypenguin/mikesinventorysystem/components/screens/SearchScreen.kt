package com.angrypenguin.mikesinventorysystem.components.screens

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.angrypenguin.mikesinventorysystem.AddNewItemActivity
import com.angrypenguin.mikesinventorysystem.contracts.InventoryTable
import com.angrypenguin.mikesinventorysystem.data.InventoryItem
import com.angrypenguin.mikesinventorysystem.models.InventoryListModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(db: SQLiteDatabase,
                 viewModel: InventoryListModel,
                 deleteItem: (item: InventoryItem) -> Unit,
                 searchBar: Boolean) {
    var context = LocalContext.current

    var input by remember { mutableStateOf("") }
    val items by viewModel.list.observeAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (searchBar) {
            Row() {
                OutlinedTextField(
                    label = {
                        Text(
                            text = "Search"
                        )
                    },
                    value = input,
                    onValueChange = {
                        input = it
                        viewModel.updateList(db, input)
                    }
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable {
                            val intent = Intent(context, AddNewItemActivity::class.java)
                            intent.putExtra("from_search", true)
                            context.startActivity(intent)
                        }
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add New Item",
                        modifier = Modifier
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    )
                }
            }
        }
        ListInventoryScreen(items = items!!, deleteItem)
    }
}