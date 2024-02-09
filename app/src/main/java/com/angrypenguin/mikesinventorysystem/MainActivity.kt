package com.angrypenguin.mikesinventorysystem

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.angrypenguin.mikesinventorysystem.components.screens.HomeScreen
import com.angrypenguin.mikesinventorysystem.contracts.DBHelper
import com.angrypenguin.mikesinventorysystem.contracts.InventoryTable
import com.angrypenguin.mikesinventorysystem.data.InventoryItem
import com.angrypenguin.mikesinventorysystem.ui.theme.MikesInventorySystemTheme
import java.io.File
import java.io.PrintWriter

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!InventoryTable.doesTableExist(db!!)) {
            InventoryTable.createTable(db!!)
        }

        setContent {
            MikesInventorySystemTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(this, ::exportFile);
                }
            }
        }
    }

    private fun exportFile() {
        var items = InventoryTable.getInventory(db!!)

        // Get downloads path
        val downloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        // Create file in downloads folder
        val file = File(downloadsPath, "mikeInventorySystem.txt")

        // Write text to file
        val writer = PrintWriter(file)
        for (item in items) {
            writer.println(item.toCSVRecord())
        }
        writer.close()
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned uri
        if (uri != null) {
            val path = uri.path // file path
            // Do something with the file
        }
    }
}