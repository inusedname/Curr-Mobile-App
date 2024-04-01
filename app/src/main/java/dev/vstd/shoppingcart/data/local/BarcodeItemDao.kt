package dev.vstd.shoppingcart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BarcodeItemDao {

    @Insert
    suspend fun insert(barcodeItem: BarcodeItem)

    @Query("SELECT * FROM barcodeitem WHERE barcode = :barcode")
    suspend fun findByBarcode(barcode: String): BarcodeItem?

    @Query("SELECT * FROM barcodeitem ORDER BY timestampAdded DESC LIMIT :limit")
    suspend fun getRecents(limit: Int = 10): List<BarcodeItem>
}