package com.angrypenguin.mikesinventorysystem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.angrypenguin.mikesinventorysystem.components.screens.SearchScreen
import com.angrypenguin.mikesinventorysystem.data.InventoryItem
import com.angrypenguin.mikesinventorysystem.models.InventoryItemModel
import com.angrypenguin.mikesinventorysystem.models.InventoryListModel
import com.angrypenguin.mikesinventorysystem.ui.theme.MikesInventorySystemTheme

class SearchActivity : BaseActivity() {
    private val viewModel: InventoryListModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var searchBar = intent.getBooleanExtra("SEARCH_BAR", true)

        setContent {
            MikesInventorySystemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SearchScreen(db!!, viewModel, ::deleteItem, searchBar)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.updateList(db!!)
    }

    private fun deleteItem(item: InventoryItem) {
        viewModel.deleteItem(db!!, item)
    }
}