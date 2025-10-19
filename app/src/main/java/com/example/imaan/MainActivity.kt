package com.example.imaan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imaan.screen.AddInventoryScreen
import com.example.imaan.screen.EditInventoryScreen
import com.example.imaan.screen.InventoryScreen
import com.example.imaan.component.InventarisMasjid.InventoryViewModel
import com.example.imaan.ui.theme.IMAANTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            IMAANTheme {
                ImaanApp()
            }
        }
    }
}

@Composable
fun ImaanApp(navController: NavHostController = rememberNavController()) {
    // ViewModel hanya dibuat sekali di sini dan dibagikan ke semua screen
    val viewModel = remember { InventoryViewModel() }

    NavHost(navController, startDestination = "inventory") {
        composable("inventory") {
            InventoryScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate("add_inventory") },
                onEditClick = { id -> navController.navigate("edit_inventory/$id") }
            )
        }
        composable("add_inventory") {
            AddInventoryScreen(
                viewModel = viewModel,
                onSaveClick = { navController.popBackStack() },
                onCancelClick = { navController.popBackStack() }
            )
        }
        composable("edit_inventory/{itemId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("itemId") ?: return@composable
            EditInventoryScreen(
                itemId = id,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
