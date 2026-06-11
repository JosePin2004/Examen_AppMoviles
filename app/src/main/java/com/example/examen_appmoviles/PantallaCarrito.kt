package com.example.examen_appmoviles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCarrito(
    viewModel: BiteBoxViewModel,
    onVolverAlMenu: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tu Orden", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        // Usamos bottomBar para dejar el total y el botón fijos en la parte inferior
        bottomBar = {
            Surface(shadowElevation = 16.dp) {
                Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total a Pagar:", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                        // CÁLCULO TOTAL EXIGIDO: Llama a la función del ViewModel
                        Text(
                            text = "$${viewModel.calcularTotal()}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Button(
                        onClick = {
                            viewModel.vaciarCarrito()

                            // Disparamos el mensaje flotante (Toast)
                            android.widget.Toast.makeText(
                                context,
                                "¡Tu pedido se ha realizado con éxito !",
                                android.widget.Toast.LENGTH_LONG
                            ).show()

                            onVolverAlMenu()
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        enabled = viewModel.carrito.isNotEmpty()
                    ) {
                        Text("Confirmar Pedido", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(viewModel.carrito) { platillo ->
                ListItem(
                    // AQUÍ AGREGAMOS LA IMAGEN DEL PEDIDO
                    leadingContent = {
                        AsyncImage(
                            model = platillo.urlImagen,
                            contentDescription = platillo.nombre,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CutCornerShape(8.dp)) // Usando el borde cortado para mantener el estilo
                        )
                    },
                    headlineContent = { Text(platillo.nombre, fontWeight = FontWeight.SemiBold) },
                    supportingContent = { Text(platillo.categoria) },
                    trailingContent = {
                        Text(
                            text = "$${platillo.precio}",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                HorizontalDivider()
            }

            if (viewModel.carrito.isEmpty()) {
                item {
                    Text(
                        text = "Tu carrito está vacío",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}