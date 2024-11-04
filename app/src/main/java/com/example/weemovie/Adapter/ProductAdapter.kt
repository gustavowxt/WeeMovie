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

class ProductAdapter(
    private var products: List<Product>,
    private val onAddToCartClicked: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val cartCounts = mutableMapOf<Product, Int>()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.productTitle)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)

        fun bind(product: Product) {
            title.text = product.title
            price.text = "R$ ${product.price}"
            Picasso.get().load(product.image).into(image)

            // Define o estado do botão com base na quantidade
            val count = cartCounts[product] ?: 0
            updateButtonState(count)

            addToCartButton.setOnClickListener {
                val newCount = if (count == 0) 1 else 0
                cartCounts[product] = newCount
                onAddToCartClicked(product)
                updateButtonState(newCount)
            }
        }

        private fun updateButtonState(count: Int) {
            if (count > 0) {
                addToCartButton.apply {
                    text = "✓ Adicionado ($count)"
                    setBackgroundColor(itemView.context.getColor(R.color.green))
                }
            } else {
                addToCartButton.apply {
                    text = "Adicionar ao Carrinho"
                    setBackgroundColor(itemView.context.getColor(R.color.default_button_color))
                }
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

