package com.example.proyecto_integrador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// este es el adaptador para el recyclerview que muestra los productos
class Adaptador(
    private val products: List<Product>,
    private val listener: RecyclerViewEvent
) : RecyclerView.Adapter<Adaptador.ProductViewHolder>() {

    // este es el viewholder que guarda las vistas que vamos a modificar en cada fila del recyclerview
    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val productName: TextView = view.findViewById(R.id.tvProductName)  // agarramos el textview para el nombre del producto
        val productPrice: TextView = view.findViewById(R.id.tvProductPrice)  // agarramos el textview para el precio del producto

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    // este método crea y devuelve un viewholder para cada ítem de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)  // devolvemos el viewholder que contiene la vista
    }

    // este método se encarga de poner los datos de cada producto en las vistas correspondientes
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]  // agarramos el producto que corresponde a la posición actual
        holder.productName.text = product.name  // seteamos el nombre del producto en el textview
        holder.productPrice.text = buildString {
            append("$ ") // Le agregamos el formato de precio
            append(product.price) // seteamos el precio del producto en el textview
        }  // seteamos el precio del producto en el textview
    }

    // este método devuelve la cantidad de ítems en la lista
    override fun getItemCount() = products.size

    interface RecyclerViewEvent {
        fun onItemClick(position: Int)
    }
}
