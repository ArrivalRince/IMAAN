package com.example.imaan.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imaan.component.InventarisMasjid.Inventory
import com.example.imaan.component.InventarisMasjid.InventoryForm
import com.example.imaan.component.InventarisMasjid.InventoryViewModel
import com.example.imaan.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInventoryScreen(
    viewModel: InventoryViewModel,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var condition by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }

    val gradient = Brush.verticalGradient(listOf(BlueLight, BlueMedium))
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Inventaris", color = BlueDark) },
                navigationIcon = {
                    IconButton(onClick = onCancelClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Kembali", tint = BlueDark)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
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
                    text = "Masukkan Data Inventaris Baru",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BlueDark
                )

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

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.95f),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ActionButton(
                        text = "SIMPAN",
                        color = BluePrimary,
                        enabled = name.isNotBlank(),
                        onClick = {
                            if (name.isNotBlank()) {
                                viewModel.addInventory(
                                    Inventory(
                                        name = name,
                                        quantity = quantity.toIntOrNull() ?: 0,
                                        condition = condition,
                                        location = location
                                    )
                                )
                                scope.launch { snackbarHostState.showSnackbar("Data berhasil ditambahkan") }
                                onSaveClick()
                            } else {
                                scope.launch { snackbarHostState.showSnackbar("Nama barang tidak boleh kosong") }
                            }
                        }
                    )

                    ActionButton(
                        text = "BATAL",
                        color = ErrorRed,
                        onClick = onCancelClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    color: Color,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(50.dp),
        enabled = enabled,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text, fontWeight = FontWeight.Bold, color = Color.White)
    }
}
