package com.angrypenguin.mikesinventorysystem.data

import java.util.Date
import java.util.UUID

class InventoryItem {

    var guid: String = UUID.randomUUID().toString()
    var barcode: String = ""
    var name: String = ""
    var qty: Int = 1
    var location: String = ""
    var date: Date = Date()

    constructor()

    constructor(
        barcode: String,
        name: String,
        qty: Int = 1,
        location: String = "",
        date: Date = Date()
    ) {
        this.barcode = barcode
        this.name = name
        this.qty = qty
        this.location = location
        this.date = date
    }

    constructor(
        guid: String,
        barcode: String,
        name: String,
        qty: Int = 1,
        location: String = "",
        date: Date = Date()
    ) {
        this.guid = guid
        this.barcode = barcode
        this.name = name
        this.qty = qty
        this.location = location
        this.date = date
    }

    fun toCSVRecord() : String {
        return "\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\""
            .format(
                guid,
                barcode,
                name,
                qty,
                location,
                date
            )
    }
}