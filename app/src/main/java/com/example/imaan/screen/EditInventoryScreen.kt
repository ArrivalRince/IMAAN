package com.example.imaan.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imaan.component.InventarisMasjid.InventoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInventoryScreen(
    itemId: String,
    viewModel: InventoryViewModel,
    onBackClick: () -> Unit
) {
    val item = viewModel.getInventoryById(itemId)
    if (item == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("", color = Color.DarkGray)
        }
        return
    }

    var name by remember { mutableStateOf(item.name) }
    var quantity by remember { mutableStateOf(item.quantity.toString()) }
    var condition by remember { mutableStateOf(item.condition) }
    var location by remember { mutableStateOf(item.location) }
    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xFFE3F2FD),
            Color(0xFFB3E5FC)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Edit Inventaris",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF003366)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color(0xFF003366)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Perbarui Data Barang",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color(0xFF003366),
                    textAlign = TextAlign.Center
                )

                // 🌸 FORM CARD
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.95f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        val labelColor = Color(0xFF37474F)   // abu tua untuk label
                        val textColor = Color(0xFF0D1B2A)    // hitam kebiruan untuk isi teks
                        val focusBlue = Color(0xFF1976D2)     // biru fokus
                        val borderBlue = Color(0xFF90CAF9)    // biru lembut border

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Nama Barang", color = labelColor) },
                            textStyle = TextStyle(color = textColor),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = focusBlue,
                                focusedLabelColor = focusBlue,
                                unfocusedBorderColor = borderBlue,
                                cursorColor = focusBlue,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = quantity,
                            onValueChange = { quantity = it },
                            label = { Text("Jumlah", color = labelColor) },
                            textStyle = TextStyle(color = textColor),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = focusBlue,
                                focusedLabelColor = focusBlue,
                                unfocusedBorderColor = borderBlue,
                                cursorColor = focusBlue,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = condition,
                            onValueChange = { condition = it },
                            label = { Text("Kondisi", color = labelColor) },
                            textStyle = TextStyle(color = textColor),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = focusBlue,
                                focusedLabelColor = focusBlue,
                                unfocusedBorderColor = borderBlue,
                                cursorColor = focusBlue,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = location,
                            onValueChange = { location = it },
                            label = { Text("Lokasi", color = labelColor) },
                            textStyle = TextStyle(color = textColor),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = focusBlue,
                                focusedLabelColor = focusBlue,
                                unfocusedBorderColor = borderBlue,
                                cursorColor = focusBlue,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            )
                        )
                    }
                }

                // 🧿 Tombol Aksi
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            val updated = item.copy(
                                name = name,
                                quantity = quantity.toIntOrNull() ?: 0,
                                condition = condition,
                                location = location
                            )
                            viewModel.updateInventory(updated)
                            onBackClick()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .clip(RoundedCornerShape(25.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3),
                            contentColor = Color.White
                        )
                    ) {
                        Text("UPDATE", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            viewModel.deleteInventory(item.id)
                            onBackClick()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .clip(RoundedCornerShape(25.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF06292),
                            contentColor = Color.White
                        )
                    ) {
                        Text("HAPUS", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
