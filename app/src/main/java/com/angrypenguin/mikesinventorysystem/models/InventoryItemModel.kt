package com.angrypenguin.mikesinventorysystem.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angrypenguin.mikesinventorysystem.data.InventoryItem
import java.util.Date

class InventoryItemModel : ViewModel() {
    private var guid: String? = null;
    var barcode = MutableLiveData("")
    var name = MutableLiveData("")
    var qty = MutableLiveData(1)
    var location = MutableLiveData("")
    var date = MutableLiveData(Date())

    fun setGuid(guid: String) {
        this.guid = guid;
    }

    fun getInventoryItem(): InventoryItem {
        if (guid != null) {
            return InventoryItem(
                guid!!,
                barcode.value!!,
                name.value!!,
                qty.value!!,
                location.value!!,
                date.value!!
            )
        }

        return InventoryItem(
            barcode.value!!,
            name.value!!,
            qty.value!!,
            location.value!!,
            date.value!!
        )
    }
}