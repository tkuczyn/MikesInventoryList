package com.angrypenguin.mikesinventorysystem.contracts

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.angrypenguin.mikesinventorysystem.data.InventoryItem
import java.util.Date

class InventoryTable  {
    companion object {
        const val TABLE_NAME = "INVENTORY"
        const val COLUMN_NAME_GUID = "GUID"
        const val COLUMN_NAME_BARCODE = "BARCODE"
        const val COLUMN_NAME_NAME = "NAME"
        const val COLUMN_NAME_QTY = "QTY"
        const val COLUMN_NAME_LOCATION = "LOCATION"
        const val COLUMN_NAME_DATE = "DATE"

        fun createTable(db: SQLiteDatabase) {
            val createTableSql = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_NAME_GUID TEXT PRIMARY KEY,  
            $COLUMN_NAME_BARCODE TEXT,
            $COLUMN_NAME_NAME TEXT,
            $COLUMN_NAME_QTY INTEGER,
            $COLUMN_NAME_LOCATION TEXT,
            $COLUMN_NAME_DATE DATETIME  
        )
    """.trimIndent()

            db.execSQL(createTableSql)
        }

        fun doesTableExist(db: SQLiteDatabase): Boolean {

            val cursor = db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='$TABLE_NAME'",
                null
            )
            val exists = cursor.count > 0

            cursor.close()
            return exists
        }

        fun getItem(db: SQLiteDatabase, guid: String): InventoryItem? {
            lateinit var item: InventoryItem

            val cursor = db.query(
                TABLE_NAME,
                null,
                "${COLUMN_NAME_GUID} = ?",
                arrayOf(guid),
                null,
                null,
                null
            )

            if (cursor.moveToFirst()) {
                item = get_item_from_cursor(cursor)
            } else {
                return null;
            }
            cursor.close();

            return item;
        }

        fun searchInventory(db: SQLiteDatabase, query: String): List<InventoryItem> {
            val selection = """
        $COLUMN_NAME_BARCODE LIKE ? 
        OR $COLUMN_NAME_NAME LIKE ? 
        OR $COLUMN_NAME_LOCATION LIKE ?
    """

            val selectionArgs = arrayOf(
                "%$query%",
                "%$query%",
                "%$query%"
            )

            var cursor = db.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            val items = mutableListOf<InventoryItem>()

            while (cursor.moveToNext()) {
                val item = get_item_from_cursor(cursor)
                items.add(item)
            }

            cursor.close()

            return items
        }

        fun getInventory(db: SQLiteDatabase): List<InventoryItem> {
            var cursor = db.query(
                        TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
            )

            val items = mutableListOf<InventoryItem>()

            while (cursor.moveToNext()) {
                val item = get_item_from_cursor(cursor)
                items.add(item)
            }

            cursor.close()

            return items
        }

        fun insertItem(db: SQLiteDatabase, item: InventoryItem) {
            val values = ContentValues().apply {
                put(COLUMN_NAME_GUID, item.guid)
                put(COLUMN_NAME_BARCODE, item.barcode)
                put(COLUMN_NAME_NAME, item.name)
                put(COLUMN_NAME_QTY, item.qty)
                put(COLUMN_NAME_LOCATION, item.location)
                put(COLUMN_NAME_DATE, item.date.time)
            }

            db.insert(TABLE_NAME, null, values)
        }

        fun updateItem(db: SQLiteDatabase, item: InventoryItem) {
            val values = ContentValues().apply {
                put(COLUMN_NAME_BARCODE, item.barcode)
                put(COLUMN_NAME_NAME, item.name)
                put(COLUMN_NAME_QTY, item.qty)
                put(COLUMN_NAME_LOCATION, item.location)
                put(COLUMN_NAME_DATE, item.date.time)
            }

            db.update(TABLE_NAME, values, "$COLUMN_NAME_GUID = ?", arrayOf(item.guid))
        }

        fun deleteItem(db: SQLiteDatabase, item: InventoryItem) {
            db.delete(TABLE_NAME, "$COLUMN_NAME_GUID = ?", arrayOf(item.guid))
        }

        private fun get_item_from_cursor(cursor: Cursor) : InventoryItem {
            return InventoryItem(
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_GUID)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_BARCODE)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NAME)),
                cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_QTY)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_LOCATION)),
                Date(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_NAME_DATE)))
            )
        }
    }
}