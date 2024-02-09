package com.angrypenguin.mikesinventorysystem.components.listitems

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.angrypenguin.mikesinventorysystem.AddNewItemActivity
import com.angrypenguin.mikesinventorysystem.data.InventoryItem
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun InventoryListItem(item: InventoryItem, deleteItem: (item: InventoryItem) -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(BorderStroke(1.5.dp, Color.Black), RoundedCornerShape(10.dp))
            .padding(10.dp)
            .clickable {
                val intent = Intent(context, AddNewItemActivity::class.java)
                intent.putExtra("guid", item.guid);
                context.startActivity(intent)
            },
    ) {
        Row() {
            Text(
                text = item.name,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "Qty: ${item.qty}"
            )
        }
        Row() {
            Box(
                Modifier.weight(1f)
            ) {
                Text(
                    text = item.location
                )
            }
            Text(
                text = item.barcode
            )
        }
        Row() {
            Box(
                Modifier.weight(1f)
            ) {
                val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
                Text(
                    text = formatter.format(item.date)
                )
            }
            Box(
                Modifier.weight(1f)
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Delete Item",
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .padding(10.dp)
                        .clickable{
                            deleteItem(item)
                        }
                )
            }
        }
    }
}