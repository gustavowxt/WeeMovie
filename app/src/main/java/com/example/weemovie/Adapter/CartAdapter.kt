package com.example.weemovie.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Product
import com.example.weemovie.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CartAdapter(
    private var cartItems: List<Pair<Product, Int>>,
    private val onQuantityChanged: (Product, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productTitle: TextView = itemView.findViewById(R.id.productTitle)
        val dateAdded: TextView = itemView.findViewById(R.id.dateAdded)
        val quantityText: TextView = itemView.findViewById(R.id.quantityText)
        val productSubtotal: TextView = itemView.findViewById(R.id.productSubtotal)
        val increaseQuantity: Button = itemView.findViewById(R.id.increaseQuantity)
        val decreaseQuantity: Button = itemView.findViewById(R.id.decreaseQuantity)

        fun bind(cartItem: Pair<Product, Int>) {
            val (product, quantity) = cartItem

            // Carrega imagem do produto com Picasso
            Picasso.get().load(product.image).into(productImage)

            // Preenche título
            productTitle.text = product.title
            dateAdded.text = "Adicionado em ${getCurrentDate()}"
            quantityText.text = quantity.toString()
            productSubtotal.text = "R$ %.2f".format(product.price * quantity)

            // Incrementa e decrementa a quantidade
            increaseQuantity.setOnClickListener {
                onQuantityChanged(product, quantity + 1)
            }

            decreaseQuantity.setOnClickListener {
                if (quantity > 1) {
                    onQuantityChanged(product, quantity - 1)
                } else {
                    onQuantityChanged(product, 0)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    override fun getItemCount() = cartItems.size

    fun updateCartItems(newCartItems: List<Pair<Product, Int>>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}


