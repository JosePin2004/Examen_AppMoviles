package com.example.examen_appmoviles

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class BiteBoxViewModel : ViewModel() {

    val menu = listOf(
        Platillo(1,
            "Pizza Pepperoni",
            "Masa crujiente cubierta con doble pepperoni, queso fundido y un toque de orégano.",
            17.90, "Pizzas",
            "https://www.tablefortwoblog.com/wp-content/uploads/2025/06/pepperoni-pizza-recipe-photos-tablefortwoblog-7-500x500.jpg"),
        Platillo(2,
            "Pizza Hawaiana",
            "Para los amantes del contraste: jamón cocido y trozos de piña dulce horneada.",
            15.50,
            "Pizzas",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgWmdMdVGqYB8sM6eZUPLCXHrXyk-NX7lMY6jWnxLuWA&s=10"),
        Platillo(3,
            "Pizza extra Queso",
            "Variante clásica caracterizada por una capa generosa de queso mozzarella derretido , que cubre por completo la salsa de tomate. Su principal atractivo es la textura cremosa, elástico al estirar y su sabor lácteo e intenso",
            13.00,
            "Pizzas",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYwbMSPAxZgwwOvhtwBUEtbo97g7wLMcSdPft-ksI9dImJJY7qmkO2EtUt&s=10"),
        Platillo(4,
            "Pizza Champiñones",
            "Salsa de tomate, mozzarella fresca, champiñones salteados y un toque de orégano.",
            13.00,
            "Pizzas",
            "https://www.clarin.com/img/2025/08/14/1MRDeaXrP_1200x630__1.jpg"),
        Platillo(5,
            "Hamburguesa Clásica",
            "Carne de res 100%, queso cheddar, lechuga fresca, tomate y salsa especial.",
            5.50,
            "Hamburguesas",
            "https://resizer.glanacion.com/resizer/v2/hamburguesa-FHBQ5XJM55H2PFSAFSC6HHESVQ.jpg?auth=c14fd6c0f7fd21e554cb59b5d69f7ee3551b78c00f500eb190a79ae39dc0ae80&width=420&height=280&quality=70&smart=true"),
        Platillo(6,
            "Hamburguesa BBQ",
            "Doble carne, aros de cebolla crujientes, tocino ahumado y abundante salsa BBQ.",
            10.00,
            "Hamburguesas",
            "https://thumbs.dreamstime.com/b/hamburguesa-del-bbq-con-tocino-y-cebollas-71619920.jpg"),
        Platillo(7,
            "Hamburguesa Doble",
            "El doble de sabor: doble medallón de carne, doble queso cheddar y pan artesanal.",
            10.50,
            "Hamburguesas",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXJk_83Bq0I6VDCNPOS8bOFQda8z8v7cFUOnHZnPM98w&s=10"),
        Platillo(8,
            "Hambuiguesa Crispy",
            "Pechuga de pollo frita extracrujiente, mayonesa de ajo, lechuga y tomate.",
            6.50,
            "Hamburguesas",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1wHokBd-rx5WYwVdWoTZwdcowTdu3NK1sshkW7lxVRQKXMMcWjQLRDfcL&s=10"),
        Platillo(9,
            "Hamburguesa Picante",
            "Doble carne, jalapeños frescos, queso pepper jack y salsa picante de la casa.",
            6.00,
            "Hamburguesas",
            "https://thumbs.dreamstime.com/b/hamburguesa-picante-con-cebollas-adobadas-y-chile-en-tablero-de-madera-aj%C3%AD-sobre-fondo-oscuro-225658843.jpg")
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