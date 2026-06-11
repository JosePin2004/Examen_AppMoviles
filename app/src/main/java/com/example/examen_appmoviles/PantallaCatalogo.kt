package com.example.examen_appmoviles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
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
                title = { Text("Bienvenido, $nombreUsuario que te gustaria ordenar?", fontWeight = FontWeight.Bold) },
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
            // Lista de Platillos (Cumpliendo la regla del LazyColumn)
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(platillosFiltrados) { platillo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp) // Un poco más de espacio vertical
                            .clickable { onNavigateToDetalle(platillo.id) },
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp) // Bordes con estilo único
                    ) {
                        // Cambiamos Row por Column para apilar imagen y texto
                        Column(modifier = Modifier.fillMaxWidth()) {
                            // Imagen ancha en la parte superior
                            AsyncImage(
                                model = platillo.urlImagen,
                                contentDescription = platillo.nombre,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                            )

                            // Textos en la parte inferior
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = platillo.nombre, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                                    Text(text = "$${platillo.precio}", fontWeight = FontWeight.Black, color = MaterialTheme.colorScheme.primary, fontSize = 18.sp)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = platillo.categoria, color = MaterialTheme.colorScheme.secondary, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
