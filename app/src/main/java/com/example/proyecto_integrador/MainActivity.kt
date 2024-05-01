package com.example.proyecto_integrador

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // acá le decimos que use el layout activity_main para mostrar la interfaz

        val productsList = mutableListOf<Product>()  // esto es una lista vacía de productos, acá vamos a meter lo que agreguemos
        val productsAdapter = Adaptador(productsList)  // creamos un adaptador que se va a encargar de mostrar los productos en el RecyclerView

        val recyclerView: RecyclerView = findViewById(R.id.rvProducts)  // encontramos el RecyclerView en el layout por su ID
        recyclerView.layoutManager = LinearLayoutManager(this)  // le decimos que muestre los ítems en forma de lista vertical
        recyclerView.adapter = productsAdapter  // le ponemos el adaptador que hicimos antes para que maneje los datos

        val addButton: Button = findViewById(R.id.btnAddProduct)  // esto busca el botón para agregar productos
        addButton.setOnClickListener {  // le ponemos un oyente que va a reaccionar cuando le den click
            val dialogView = LayoutInflater.from(this).inflate(R.layout.add_product, null)  // hacemos que se muestre
            val productNameEditText = dialogView.findViewById<EditText>(R.id.etProductName)  // buscamos el campo para poner el nombre del producto
            val productPriceEditText = dialogView.findViewById<EditText>(R.id.etProductPrice)  // buscamos el campo para poner el precio

            AlertDialog.Builder(this)  // creamos un diálogo para interactuar con el usuario
                .setTitle("Agregar Producto")  // le ponemos un título al diálogo
                .setView(dialogView)  // acá le decimos que use el layout que inflamos antes para el diálogo
                .setPositiveButton("Agregar") { dialog, which ->  // un botón para agregar el producto
                    val name = productNameEditText.text.toString()  // tomamos el nombre del producto del campo de texto
                    val price = productPriceEditText.text.toString().toDoubleOrNull() ?: 0.0  // tomamos el precio y lo convertimos a número, si hay lío lo dejamos en 0
                    val newProduct = Product(name, price)  // creamos un nuevo producto con el nombre y precio
                    productsList.add(newProduct)  // añadimos el producto a la lista
                    productsAdapter.notifyItemInserted(productsList.size - 1)  // avisamos al adaptador que agregamos algo nuevo pata que se actualice
                }
                .setNegativeButton("Cancelar", null)  // un botón por si se arrepienten y no quieren agregar nada
                .show()  // mostramos el diálogo
        }
    }

}
