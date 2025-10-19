package com.example.imaan.screen

import com.example.imaan.ui.theme.*
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imaan.component.InventarisMasjid.InventoryViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(
    viewModel: InventoryViewModel,
    onAddClick: () -> Unit,
    onEditClick: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val filteredList = viewModel.inventoryList.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.location.contains(searchQuery, ignoreCase = true)
    }

    val blueGradient = Brush.verticalGradient(listOf(BlueLight, BlueMedium))

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = BluePrimary,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Inventaris")
            }
        },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(blueGradient)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                //Header
                Text(
                    text = "Inventaris Masjid",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = BlueDark
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Data perlengkapan dan fasilitas masjid",
                    style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                )

                Spacer(Modifier.height(20.dp))

                //Pencarian
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        if (it.isNotEmpty() && filteredList.isEmpty()) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Tidak ditemukan hasil untuk \"$searchQuery\"",
                                    withDismissAction = true
                                )
                            }
                        }
                    },
                    label = { Text("Cari inventaris...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BlueAccent,
                        focusedLabelColor = BlueAccent
                    )
                )

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = onAddClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BlueAccent,
                        contentColor = Color.White
                    )
                ) {
                    Text("Tambahkan Inventaris", fontWeight = FontWeight.Medium)
                }

                Spacer(Modifier.height(16.dp))

                // Daftar
                if (filteredList.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Tidak ada data Inventaris",
                            color = TextHint,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(filteredList) { item ->
                            AnimatedVisibility(
                                visible = true,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {
                                ElevatedCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .animateContentSize(),
                                    shape = RoundedCornerShape(24.dp),
                                    colors = CardDefaults.elevatedCardColors(containerColor = CardBackground),
                                    elevation = CardDefaults.cardElevation(4.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .background(
                                                Brush.linearGradient(
                                                    listOf(Color.White, BlueLight)
                                                ),
                                                shape = RoundedCornerShape(24.dp)
                                            )
                                    ) {
                                        Text(
                                            text = item.name,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = TextPrimary
                                            )
                                        )

                                        Spacer(Modifier.height(6.dp))
                                        Text("Jumlah: ${item.quantity}", color = TextMuted)
                                        Text("Kondisi: ${item.condition}", color = TextMuted)
                                        Text("Lokasi: ${item.location}", color = TextMuted)

                                        Spacer(Modifier.height(10.dp))

                                        Row(
                                            horizontalArrangement = Arrangement.End,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            TextButton(
                                                onClick = {
                                                    onEditClick(item.id)
                                                    scope.launch {
                                                        snackbarHostState.showSnackbar(
                                                            message = "Membuka halaman edit untuk ${item.name}",
                                                            withDismissAction = true
                                                        )
                                                    }
                                                },
                                                colors = ButtonDefaults.textButtonColors(
                                                    contentColor = BluePrimary
                                                )
                                            ) {
                                                Text("Edit")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
