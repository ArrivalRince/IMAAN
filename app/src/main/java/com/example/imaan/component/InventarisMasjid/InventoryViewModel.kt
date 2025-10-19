package com.example.imaan.component.InventarisMasjid


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.util.UUID

// Model data inventaris
data class Inventory(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val quantity: Int,
    val condition: String,
    val location: String
)

// ViewModel untuk mengelola data
class InventoryViewModel : ViewModel() {

    // Daftar inventaris disimpan di memory
    private val _inventoryList = mutableStateListOf<Inventory>()
    val inventoryList: List<Inventory> get() = _inventoryList

    // Tambah item baru
    fun addInventory(item: Inventory) {
        _inventoryList.add(item)
    }

    fun updateInventory(updatedItem: Inventory) {
        val index = _inventoryList.indexOfFirst { it.id == updatedItem.id }
        if (index != -1) {
            _inventoryList[index] = updatedItem
        }
    }

    fun deleteInventory(id: String) {
        _inventoryList.removeAll { it.id == id }
    }

    fun getInventoryById(id: String): Inventory? {
        return _inventoryList.find { it.id == id }
    }

    fun loadSampleData() {
        _inventoryList.clear()
        _inventoryList.addAll(
            listOf(
                Inventory(
                    name = "Kursi Lipat Masjid",
                    quantity = 15,
                    condition = "Baik",
                    location = "Ruang Utama"
                ),
                Inventory(
                    name = "Karpet Hijau",
                    quantity = 5,
                    condition = "Baru",
                    location = "Depan Mihrab"
                ),
                Inventory(
                    name = "Speaker",
                    quantity = 2,
                    condition = "Baik",
                    location = "Menara"
                )
            )
        )
    }
}
