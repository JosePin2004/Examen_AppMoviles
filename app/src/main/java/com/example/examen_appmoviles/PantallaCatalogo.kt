package com.example.examen_appmoviles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogo(
    nombreUsuario: String,
    viewModel: BiteBoxViewModel,
    onNavigateToDetalle: (Int) -> Unit,
    onNavigateToCarrito: () -> Unit
) {
    // Estado para saber qué filtro está presionado
    var filtroActivo by remember { mutableStateOf("Todos") }

    // Lógica para filtrar la lista basándonos en el filtro activo
    val platillosFiltrados = if (filtroActivo == "Todos") {
        viewModel.menu
    } else {
        viewModel.menu.filter { it.categoria == filtroActivo }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hola, $nombreUsuario", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCarrito,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Ir al carrito",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            // Fila de Filtros (Chips)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf("Todos", "Pizzas", "Hamburguesas").forEach { categoria ->
                    FilterChip(
                        selected = filtroActivo == categoria,
                        onClick = { filtroActivo = categoria },
                        label = { Text(categoria) }
                    )
                }
            }

            // Lista de Platillos
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(platillosFiltrados) { platillo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { onNavigateToDetalle(platillo.id) }, // Navega enviando el ID
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Imagen cargada de internet con Coil
                            AsyncImage(
                                model = platillo.urlImagen,
                                contentDescription = platillo.nombre,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            // Textos de la tarjeta
                            Column {
                                Text(text = platillo.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text(text = platillo.categoria, color = MaterialTheme.colorScheme.secondary, fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = "$${platillo.precio}", fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }
        }
    }
}
