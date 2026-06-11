package com.example.examen_appmoviles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaBienvenida(onNavigateToMenu: (String) -> Unit) {
    // Estado para guardar lo que el usuario escribe
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo en Imagen
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Ingresa tu nombre") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigateToMenu(nombre) },
            // LÓGICA EXIGIDA: El botón solo se habilita si hay más de 3 letras
            enabled = nombre.trim().length > 3,
            modifier = Modifier.width(200.dp)
        ) {
            Text("Entrar")
        }
    }
}