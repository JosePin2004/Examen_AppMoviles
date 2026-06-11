package com.example.examen_appmoviles

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class BiteBoxViewModel : ViewModel() {

    // Menú hardcoded de 6 platillos
    val menu = listOf(
        Platillo(1, "Pizza Margarita", "Clásica pizza italiana con salsa de tomate de la casa, mozzarella fresca y albahaca.", 10.50, "Pizzas", "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?q=80&w=400"),
        Platillo(2, "Pizza Pepperoni", "Masa crujiente cubierta con doble pepperoni, queso fundido y un toque de orégano.", 12.00, "Pizzas", "https://images.unsplash.com/photo-1628840042765-356cda07504e?q=80&w=400"),
        Platillo(3, "Pizza Hawaiana", "Para los amantes del contraste: jamón cocido y trozos de piña dulce horneada.", 11.50, "Pizzas", "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?q=80&w=400"),
        Platillo(4, "Burger Clásica", "Carne de res 100%, queso cheddar, lechuga fresca, tomate y salsa especial.", 8.50, "Hamburguesas", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?q=80&w=400"),
        Platillo(5, "Burger BBQ", "Doble carne, aros de cebolla crujientes, tocino ahumado y abundante salsa BBQ.", 10.00, "Hamburguesas", "https://images.unsplash.com/photo-1594212586326-0e1ce470e8df?q=80&w=400"),
        Platillo(6, "Burger Doble", "El doble de sabor: doble medallón de carne, doble queso cheddar y pan artesanal.", 12.50, "Hamburguesas", "https://images.unsplash.com/photo-1586190848861-99aa4a171e90?q=80&w=400")
    )

    // Lista reactiva para el carrito
    private val _carrito = mutableStateListOf<Platillo>()
    val carrito: List<Platillo> get() = _carrito

    // Funciones de negocio exigidas
    fun agregarAlCarrito(platillo: Platillo) {
        _carrito.add(platillo)
    }

    fun vaciarCarrito() {
        _carrito.clear()
    }

    fun calcularTotal(): Double {
        return _carrito.sumOf { it.precio }
    }

    fun obtenerPlatilloPorId(id: Int): Platillo? {
        return menu.find { it.id == id }
    }
}