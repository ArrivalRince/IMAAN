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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.imaan.component.InventarisMasjid.InventoryCard
import com.example.imaan.component.InventarisMasjid.InventoryForm
import com.example.imaan.component.InventarisMasjid.InventoryViewModel
import com.example.imaan.ui.theme.*
import kotlinx.coroutines.launch

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
            Text("Data tidak ditemukan", color = BlueDark)
        }
        return
    }

    var name by remember { mutableStateOf(item.name) }
    var quantity by remember { mutableStateOf(item.quantity.toString()) }
    var condition by remember { mutableStateOf(item.condition) }
    var location by remember { mutableStateOf(item.location) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val gradient = Brush.verticalGradient(listOf(BlueLight, BlueMedium))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Inventaris", color = BlueDark) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Kembali", tint = BlueDark)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor =Color.Transparent)
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Perbarui Data Barang",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BlueDark
                )

                InventoryCard {
                    InventoryForm(
                        name = name,
                        quantity = quantity,
                        condition = condition,
                        location = location,
                        onNameChange = { name = it },
                        onQuantityChange = { quantity = it },
                        onConditionChange = { condition = it },
                        onLocationChange = { location = it }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                            scope.launch { snackbarHostState.showSnackbar("Data diperbarui") }
                            onBackClick()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
                    ) {
                        Text("UPDATE", fontWeight = FontWeight.Bold, color = Color.White)
                    }

                    Button(
                        onClick = {
                            viewModel.deleteInventory(item.id)
                            scope.launch { snackbarHostState.showSnackbar("Data dihapus") }
                            onBackClick()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DeletePink)
                    ) {
                        Text("HAPUS", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}