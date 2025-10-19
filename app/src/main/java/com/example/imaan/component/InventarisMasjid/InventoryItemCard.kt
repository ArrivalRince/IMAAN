package com.example.imaan.component.InventarisMasjid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import com.example.imaan.ui.theme.*
import java.util.UUID

@Composable
fun InventoryItemCard(
    name: String,
    quantity: Int,
    condition: String,
    location: String
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = name, fontWeight = FontWeight.Bold, color = TextPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Jumlah: $quantity", color = TextMuted)
            Text(text = "Kondisi: $condition", color = TextMuted)
            Text(text = "Lokasi: $location", color = TextMuted)
        }
    }
}

data class Inventory(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val quantity: Int,
    val condition: String,
    val location: String
)

class InventoryViewModel : ViewModel() {

    var inventoryList = mutableStateListOf(
        Inventory(
            name = "Karpet Masjid",
            quantity = 5,
            condition = "Baik",
            location = "Ruang Utama"
        ),
        Inventory(name = "Speaker Aktif", quantity = 2, condition = "Cukup", location = "Gudang")
    )
        private set

    fun addInventory(item: Inventory) {
        inventoryList.add(item)
    }

    fun updateInventory(updated: Inventory) {
        val index = inventoryList.indexOfFirst { it.id == updated.id }
        if (index != -1) inventoryList[index] = updated
    }

    fun deleteInventory(id: String) {
        inventoryList.removeAll { it.id == id }
    }

    fun getInventoryById(id: String): Inventory? = inventoryList.find { it.id == id }
}