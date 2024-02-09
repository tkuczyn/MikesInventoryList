package com.angrypenguin.mikesinventorysystem.models

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrypenguin.mikesinventorysystem.contracts.InventoryTable
import com.angrypenguin.mikesinventorysystem.data.InventoryItem

class InventoryListModel  : ViewModel() {
    var list = MutableLiveData<MutableList<InventoryItem>>(mutableListOf())
    var input = "";

    fun updateList(db: SQLiteDatabase, input: String) {
        this.input = input
        list.value = InventoryTable.searchInventory(db, input).toMutableList()
    }

    fun updateList(db:SQLiteDatabase) {
        list.value = InventoryTable.searchInventory(db, input).toMutableList()
    }

    fun deleteItem(db: SQLiteDatabase, item: InventoryItem) {
        InventoryTable.deleteItem(db, item)
        updateList(db)
    }
}