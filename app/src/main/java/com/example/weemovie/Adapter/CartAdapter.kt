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
    private var cartItems: MutableList<Product>,
    private val onQuantityChanged: (Product, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val cartCounts = mutableMapOf<Product, Int>()  // Mapa para armazenar produtos e suas quantidades

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.cartItemTitle)
        val quantity: TextView = itemView.findViewById(R.id.cartItemQuantity)
        val price: TextView = itemView.findViewById(R.id.cartItemPrice)
        val removeButton: Button = itemView.findViewById(R.id.cartItemRemoveButton)

        fun bind(product: Product) {
            val productQuantity = cartCounts[product] ?: 1
            title.text = product.title
            quantity.text = productQuantity.toString()
            price.text = "R$ %.2f".format(product.price * productQuantity)

            removeButton.setOnClickListener {
                removeProduct(product)
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

    /**
     * Atualiza o conteúdo do adapter com uma nova lista de produtos e suas quantidades.
     */
    fun updateCartItems(newCartItems: Map<Product, Int>) {
        cartItems.clear()
        cartItems.addAll(newCartItems.keys)
        cartCounts.clear()
        cartCounts.putAll(newCartItems)
        notifyDataSetChanged()
    }

    /**
     * Adiciona um produto ao carrinho ou aumenta sua quantidade, se ele já estiver no carrinho.
     */
    fun updateCart(product: Product) {
        if (cartCounts.containsKey(product)) {
            cartCounts[product] = (cartCounts[product] ?: 0) + 1
        } else {
            cartItems.add(product)
            cartCounts[product] = 1
        }
        onQuantityChanged(product, cartCounts[product] ?: 1)
        notifyDataSetChanged()
    }

    /**
     * Remove um produto do carrinho.
     */
    private fun removeProduct(product: Product) {
        cartCounts.remove(product)
        cartItems.remove(product)
        onQuantityChanged(product, 0)
        notifyDataSetChanged()
    }

    /**
     * Calcula o preço total do carrinho.
     */
    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.price * (cartCounts[it] ?: 1) }
    }
}

