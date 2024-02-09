package com.angrypenguin.mikesinventorysystem.components.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.angrypenguin.mikesinventorysystem.AddNewItemActivity
import com.angrypenguin.mikesinventorysystem.BaseActivity
import com.angrypenguin.mikesinventorysystem.contracts.InventoryTable
import com.angrypenguin.mikesinventorysystem.models.InventoryItemModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewItem(context: Context, guid: String?, from_search: Boolean, viewModel: InventoryItemModel) {
    var activity = LocalContext.current as BaseActivity

    val barcode by viewModel.barcode.observeAsState("")
    val name by viewModel.name.observeAsState("")
    var qtyString by remember { mutableStateOf("")}
    val location by viewModel.location.observeAsState("")

    val date by viewModel.date.observeAsState(initial = Date())
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    var dateString by remember { mutableStateOf(formatter.format(date)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = {
                Text (
                    text = "Barcode"
                )
            },
            value = barcode,
            onValueChange = {
                viewModel.barcode.value = it
            }
        )
        OutlinedTextField(
            label = {
                Text (
                    text = "Name"
                )
            },
            value = name,
            onValueChange = {
                viewModel.name.value = it
            }
        )
        OutlinedTextField(
            label = {
                Text (
                    text = "Quantity"
                )
            },
            value = qtyString,
            onValueChange = {
                qtyString = it
                try {
                    viewModel.qty.value = it.toInt()
                } catch (e: Exception){
                    //do nothing
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            label = {
                Text (
                    text = "Location"
                )
            },
            value = location,
            onValueChange = {
                viewModel.location.value = it
            }
        )
        OutlinedTextField(
            value = dateString,
            label = {
                Text (
                    text = "Date"
                )
            },
            onValueChange = {
                try {
                    dateString = it
                    viewModel.date.value = formatter.parse(dateString)
                } catch (e: Exception) {

                }
            }
        )

        Button(
            onClick = {
                activity?.db?.let {
                    var item = viewModel.getInventoryItem()
                    if (guid == null) {
                        InventoryTable.insertItem(it,item)

                        if (!from_search) {
                            val intent = Intent(context, AddNewItemActivity::class.java)
                            context.startActivity(intent)
                        }
                    } else {
                        InventoryTable.updateItem(it, item)
                    }
                }
                activity?.finish();
            },
            content = {
                Text (text = "Save")
            },
            modifier = Modifier
                .fillMaxWidth(1F)
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
    }
}