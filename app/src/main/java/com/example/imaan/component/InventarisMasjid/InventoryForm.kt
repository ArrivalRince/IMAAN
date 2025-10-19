package com.example.imaan.component.InventarisMasjid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
    onLocationChange: (String) -> Unit,
) {
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = FocusBlue,
        focusedLabelColor = FocusBlue,
        unfocusedBorderColor = BorderBlue,
        cursorColor = FocusBlue,
        focusedContainerColor = CardBackground,
        unfocusedContainerColor = CardBackground
    )

    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Nama Barang", color = TextLabel) },
        textStyle = TextStyle(color = TextContent),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = fieldColors
    )

    OutlinedTextField(
        value = quantity,
        onValueChange = onQuantityChange,
        label = { Text("Jumlah", color = TextLabel) },
        textStyle = TextStyle(color = TextContent),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = fieldColors
    )

    OutlinedTextField(
        value = condition,
        onValueChange = onConditionChange,
        label = { Text("Kondisi", color = TextLabel) },
        textStyle = TextStyle(color = TextContent),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = fieldColors
    )

    OutlinedTextField(
        value = location,
        onValueChange = onLocationChange,
        label = { Text("Lokasi", color = TextLabel) },
        textStyle = TextStyle(color = TextContent),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = fieldColors
    )
}