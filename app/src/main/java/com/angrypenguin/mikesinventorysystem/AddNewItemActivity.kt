package com.angrypenguin.mikesinventorysystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.angrypenguin.mikesinventorysystem.components.screens.AddNewItem
import com.angrypenguin.mikesinventorysystem.contracts.InventoryTable
import com.angrypenguin.mikesinventorysystem.models.InventoryItemModel
import com.angrypenguin.mikesinventorysystem.ui.theme.MikesInventorySystemTheme

class AddNewItemActivity : BaseActivity() {
    private val viewModel: InventoryItemModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var guid = intent.getStringExtra("guid")
        var from_search = intent.getBooleanExtra("from_search", false)

        if (guid != null) {
            viewModel.setGuid(guid)

            var item = InventoryTable.getItem(db!!, guid!!)
            if (item != null) {
                viewModel.barcode.value = item.barcode
                viewModel.name.value = item.name
                viewModel.location.value = item.location
                viewModel.date.value = item.date
                viewModel.qty.value = item.qty
            }
        }

        setContent {
            MikesInventorySystemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddNewItem(this, guid, from_search, viewModel)
                }
            }
        }
    }
}