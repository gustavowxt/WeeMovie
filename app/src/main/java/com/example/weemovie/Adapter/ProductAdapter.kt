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
import com.squareup.picasso.Picasso

class ProductAdapter(
    private var products: List<Product>,
    private val onAddToCartClicked: (Product) -> Unit
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

            addToCartButton.setOnClickListener {
                onAddToCartClicked(product)
                addToCartButton.apply {
                    text = "✓ Adicionado"
                    setBackgroundColor(itemView.context.getColor(R.color.green))
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

    // Método para atualizar a lista de produtos
    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

}
