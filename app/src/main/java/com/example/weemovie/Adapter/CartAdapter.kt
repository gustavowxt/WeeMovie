package com.example.weemovie.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weemovie.Product
import com.example.weemovie.R

class CartAdapter(
    private val cartItems: MutableList<Product>,
    private val onRemoveItem: (Product) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cart_product, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]
        holder.bind(product)
        holder.removeButton.setOnClickListener {
            onRemoveItem(product)
        }
    }

    override fun getItemCount() = cartItems.size

    // função para atualizar os itens do carrinho
    fun updateCartItems(newCartItems: List<Product>) {
        cartItems.clear()
        cartItems.addAll(newCartItems)
        notifyDataSetChanged()
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.cartProductName)
        private val productPrice: TextView = itemView.findViewById(R.id.cartProductPrice)
        private val productImage: ImageView = itemView.findViewById(R.id.cartProductImage)
        val removeButton: Button = itemView.findViewById(R.id.removeFromCartButton)


        fun bind(product: Product) {
            productName.text = product.title
            productPrice.text = "R$ ${product.price}"

//        fun bind(product: Product) {
//            title.text = product.title
//            price.text = "R$ ${product.price}"
//            Glide.with(itemView).load(product.image).into(image)
//
//            removeButton.setOnClickListener {
//                onRemoveItem(product)
//                notifyItemRemoved(adapterPosition)
//            }
//        }
        }
    }
}
