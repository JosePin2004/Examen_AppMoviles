package com.example.examen_appmoviles

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PantallaDetalle(
    platilloId: Int,
    viewModel: BiteBoxViewModel,
    onBack: () -> Unit
) {
    // Buscamos el platillo específico usando la función del ViewModel
    val platillo = viewModel.obtenerPlatilloPorId(platilloId)

    if (platillo != null) {
        Column(modifier = Modifier.fillMaxSize()) {

            // UI Inmersiva: Imagen grande en la parte superior
            AsyncImage(
                model = platillo.urlImagen,
                contentDescription = platillo.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                // Información del platillo
                Text(text = platillo.nombre, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "$${platillo.precio}",
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = platillo.descripcion, fontSize = 16.sp, lineHeight = 24.sp)

                // Este Spacer empuja el botón hacia el fondo de la pantalla
                Spacer(modifier = Modifier.weight(1f))

                // Botón de Interacción
                Button(
                    onClick = {
                        // 1. Añadimos a la lista reactiva del ViewModel
                        viewModel.agregarAlCarrito(platillo)
                        // 2. Regresamos automáticamente a la pantalla anterior
                        onBack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text("Añadir al Carrito", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    } else {
        // Por si ocurre un error y el ID no existe
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Platillo no encontrado")
        }
    }
}