package com.angrypenguin.mikesinventorysystem.contacts

import android.database.sqlite.SQLiteDatabase
import com.angrypenguin.mikesinventorysystem.contracts.InventoryTable
import com.angrypenguin.mikesinventorysystem.data.InventoryItem
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertTrue
import org.junit.Test

//class InventoryTableTest {
//
//    private val db = mockk<SQLiteDatabase>(relaxed = true)
//    private val table = InventoryTable()
//
//    @Test
//    fun `creates table if it doesn't exist`() {
//        table.createTable(db)
//
//        verify(db).execSQL(contains("CREATE TABLE IF NOT EXISTS"))
//    }
//
//    @Test
//    fun `table exist check works`() {
//        table.createTable(db)
//
//        val exists = table.doesTableExist(db)
//
//        assertTrue(exists)
//    }
//
//    @Test
//    fun `can search inventory by partial match`() {
//        // Populate some test data
//
//        val results = table.searchInventory(db, "office")
//
//        assertTrue(results.isNotEmpty())
//        assertTrue(results[0].location.contains("office"))
//    }
//
//    @Test
//    fun `can insert new item`() {
//        val guid = "test_guid"
//        val item = InventoryItem(guid = guid, ...)
//
//        table.insertItem(db, item)
//
//        verify(db).insert(TABLE_NAME, null, hasValue(guid))
//    }
//
//    @Test
//    fun `can update existing item`() {
//        // Insert
//        val item = InventoryItem(...)
//        table.insertItem(db, item)
//
//        // Update
//        item.qty = 15
//        table.updateItem(db, item)
//
//        verify(db).update(eq(TABLE_NAME), hasValue(15), any(), any())
//    }
//}