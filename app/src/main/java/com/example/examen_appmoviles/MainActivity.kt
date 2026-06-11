package com.example.examen_appmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examen_appmoviles.ui.theme.Examen_AppMovilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen_AppMovilesTheme { // Reemplaza esto si tu tema tiene otro nombre
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BiteBoxApp()
                }
            }
        }
    }
}

@Composable
fun BiteBoxApp() {
    // Controladores de navegación y estado global
    val navController = rememberNavController()
    val viewModel: BiteBoxViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {

        // RUTA 1: Pantalla de Bienvenida
        composable("login") {
            PantallaBienvenida(onNavigateToMenu = { nombreUsuario ->
                navController.navigate("menu/$nombreUsuario")
            })
        }

        // RUTA 2: Menú (Recibe el nombre por ruta)
        composable(
            route = "menu/{nombre}",
            arguments = listOf(navArgument("nombre") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: "Invitado"

            PantallaCatalogo(
                nombreUsuario = nombre,
                viewModel = viewModel,
                // Dejamos las rutas listas para las pantallas 3 y 4 (temporales por ahora)
                onNavigateToDetalle = { platilloId -> navController.navigate("detalle/$platilloId") },
                onNavigateToCarrito = { navController.navigate("carrito") }
            )
        }

        // (Aquí arriba está tu ruta de login y la ruta de menu/{nombre})

        // RUTA 3: Detalle (Recibe el ID del platillo por la ruta)
        composable(
            route = "detalle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            // Extraemos el ID numérico de la ruta
            val platilloId = backStackEntry.arguments?.getInt("id") ?: 0

            PantallaDetalle(
                platilloId = platilloId,
                viewModel = viewModel,
                onBack = { navController.popBackStack() } // popBackStack te devuelve a la vista anterior
            )
        }

        // (Tus otras rutas están aquí arriba)

        // RUTA 4: Carrito
        composable("carrito") {
            PantallaCarrito(
                viewModel = viewModel,
                onVolverAlMenu = {
                    // Devuelve a Pantalla 2 (Catálogo)
                    navController.popBackStack()
                }
            )
        }
    } // <- Este es el cierre del NavHost
} // <- Este es el cierre de la función BiteBoxApp

