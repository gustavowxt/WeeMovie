package com.example.weemovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.ProductAdapter

class HomeFragment : Fragment() {

    private lateinit var productAdapter: ProductAdapter
    private var products = listOf<Product>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(context)

        productAdapter = ProductAdapter(products) { product ->
            (activity as? HomeActivity)?.addToCart(product)
        }
        recyclerView.adapter = productAdapter

        // Mock de produtos para exibição
        products = listOf(
            Product(1, "Viúva Negra", 9.99, "https://link-imagem/viuva-negra.png"),
            Product(2, "Shang-Chi", 30.99, "https://link-imagem/shang-chi.png"),
            Product(3, "Homem-Aranha", 29.99, "https://link-imagem/homem-aranha.png")
        )
        productAdapter.updateProducts(products)

        return view
    }
}






