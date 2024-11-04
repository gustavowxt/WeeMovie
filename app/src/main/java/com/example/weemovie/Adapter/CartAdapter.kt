package com.example.weemovie.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Product
import com.example.weemovie.R

class CartAdapter(
    private val cartItems: MutableList<Product>,
    private val onQuantityChanged: (Product, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val cartCounts = mutableMapOf<Product, Int>()

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.cartItemTitle)
        val quantity: TextView = itemView.findViewById(R.id.cartItemQuantity)
        val price: TextView = itemView.findViewById(R.id.cartItemPrice)
        val removeButton: Button = itemView.findViewById(R.id.cartItemRemoveButton)

        fun bind(product: Product) {
            title.text = product.title
            quantity.text = cartCounts[product]?.toString() ?: "1"
            price.text = "R$ ${product.price * (cartCounts[product] ?: 1)}"

            removeButton.setOnClickListener {
                cartCounts.remove(product)
                cartItems.remove(product)
                notifyDataSetChanged()
                onQuantityChanged(product, 0)
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

    fun updateCart(product: Product) {
        if (cartCounts.containsKey(product)) {
            cartCounts[product] = (cartCounts[product] ?: 0) + 1
        } else {
            cartItems.add(product)
            cartCounts[product] = 1
        }
        notifyDataSetChanged()
    }

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.price * (cartCounts[it] ?: 1) }
    }
}

