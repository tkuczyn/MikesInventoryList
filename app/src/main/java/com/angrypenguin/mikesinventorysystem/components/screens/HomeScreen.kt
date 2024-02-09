package com.angrypenguin.mikesinventorysystem.components.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angrypenguin.mikesinventorysystem.AddNewItemActivity
import com.angrypenguin.mikesinventorysystem.SearchActivity

@Composable
fun HomeScreen(context: Context, exportFile: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                val intent = Intent(context, AddNewItemActivity::class.java)
                context.startActivity(intent)
            },
            content = {
                Text (text = "Add New Items")
            },
            modifier = Modifier
                .fillMaxWidth(1F)
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
        Button(
            onClick = {
                val intent = Intent(context, SearchActivity::class.java)
                context.startActivity(intent)
            },
            content = {
                Text (text = "Search")
            },
            modifier = Modifier
                .fillMaxWidth(1F)
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
            )
        Button(
            onClick = {
                val intent = Intent(context, SearchActivity::class.java)
                intent.putExtra("SEARCH_BAR", false)
                context.startActivity(intent)
            },
            content = {
                Text (text = "View All")
            },
            modifier = Modifier
                .fillMaxWidth(1F)
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
        Button(
            onClick = {
                exportFile()
            },
            content = {
                Text (text = "Export")
            },
            modifier = Modifier
                .fillMaxWidth(1F)
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        )
    }
}