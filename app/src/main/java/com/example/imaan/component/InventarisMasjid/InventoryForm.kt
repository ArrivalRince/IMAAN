package com.example.imaan.component.InventarisMasjid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.imaan.ui.theme.*

@Composable
fun InventoryForm(
    name: String,
    quantity: String,
    condition: String,
    location: String,
    onNameChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit,
    onConditionChange: (String) -> Unit,
    onLocationChange: (String) -> Unit
) {
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = BluePrimary,
        focusedLabelColor = TextPrimary,
        unfocusedLabelColor = TextPrimary,
        unfocusedBorderColor = BorderBlue,
        unfocusedContainerColor = Color.White,
        focusedContainerColor = Color.White
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Nama Barang") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = fieldColors
        )

        OutlinedTextField(
            value = quantity,
            onValueChange = onQuantityChange,
            label = { Text("Jumlah") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = fieldColors
        )

        OutlinedTextField(
            value = condition,
            onValueChange = onConditionChange,
            label = { Text("Kondisi") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = fieldColors
        )

        OutlinedTextField(
            value = location,
            onValueChange = onLocationChange,
            label = { Text("Lokasi") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = fieldColors
        )
    }
}