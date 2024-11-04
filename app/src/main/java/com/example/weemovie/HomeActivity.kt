package com.example.weemovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.ProductAdapter
import com.example.weemovie.Repository.MockProductRepository
import com.example.weemovie.Repository.ProductRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    private val cartItems = mutableListOf<Product>() // Lista dos itens no carrinho
    private lateinit var bottomNavigation: BottomNavigationView
    private val repository: ProductRepository =
        MockProductRepository() // Se trocar para NetworkProductRepository usa a API real, e para MockProductRepository usa os dados mockados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation = findViewById(R.id.bottom_navigation)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializa o adapter com uma lista vazia e define a ação para adicionar ao carrinho
        adapter = ProductAdapter(emptyList()) { product ->
            cartItems.add(product)
            Snackbar.make(
                findViewById(android.R.id.content),
                "${product.title} adicionado ao carrinho!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        recyclerView.adapter = adapter

        // Carrega os produtos da API
        fetchProducts()
    }

    // Função para ser a chamada ao adicionar um produto ao carrinho
    private fun onAddToCartClicked(product: Product) {
        // Verifica se o produto já está no carrinho
        if (!cartItems.contains(product)) {
            cartItems.add(product)
            Snackbar.make(
                findViewById(android.R.id.content),
                "${product.title} adicionado ao carrinho!",
                Snackbar.LENGTH_SHORT
            ).show()
            // Atualiza o contador de itens no ícone de carrinho
            updateCartBadge(cartItems.size)
        }
    }

    // Função para atualizar o contador de itens do carrinho na BottomNavigationView
    private fun updateCartBadge(count: Int) {
        val menuItem = bottomNavigation.menu.findItem(R.id.navigation_cart)
        menuItem.title = "Carrinho ($count)"
    }

    // Função para buscar produtos da API e atualizar o adapter
    private fun fetchProducts() {
        // Dados fictícios para teste
        repository.getProducts { products ->
            adapter.updateProducts(products)
        }
    }
}

