package com.example.weemovie.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Product
import com.example.weemovie.R
import com.squareup.picasso.Picasso

class ProductAdapter(
    private var products: List<Product>,
    private val onProductToggle: (Product) -> Boolean,       // Alterna o filme no carrinho
    private val isProductInCart: (Product) -> Boolean,       // Verifica se o filme está no carrinho
    private val getProductQuantity: (Product) -> Int         // A quantidade do filme no carrinho
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.productTitle)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)

        fun bind(product: Product) {
            title.text = product.title
            price.text = "R$ ${product.price}"
            Picasso.get().load(product.image).into(image)

            // Define o estado inicial do botão com base no carrinho
            val isAdded = isProductInCart(product)
            updateButtonState(isAdded, getProductQuantity(product))

            addToCartButton.setOnClickListener {
                val added = onProductToggle(product)
                updateButtonState(added, getProductQuantity(product))
                val message = if (added) {
                    "${product.title} adicionado ao carrinho"
                } else {
                    "${product.title} removido do carrinho"
                }
                Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()
            }
        }

        private fun updateButtonState(isAdded: Boolean, quantity: Int) {
            if (isAdded) {
                addToCartButton.text = "Adicionado ($quantity)"
                addToCartButton.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.green)
                )
            } else {
                addToCartButton.text = "Adicionar ao carrinho"
                addToCartButton.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.default_button_color)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}


