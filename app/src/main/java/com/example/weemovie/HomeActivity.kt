package com.example.weemovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.ProductAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var adapter: ProductAdapter
    private val cartItems = mutableListOf<Product>() // Lista dos itens no carrinho
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation = findViewById(R.id.bottom_navigation)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializa o adapter com uma lista vazia e define a ação para adicionar ao carrinho
        adapter = ProductAdapter(emptyList()) { product ->
            onAddToCartClicked(product)
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
            Snackbar.make(findViewById(android.R.id.content), "${product.title} adicionado ao carrinho!", Snackbar.LENGTH_SHORT).show()
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
        val mockProducts = listOf(
            Product(1, "Viúva Negra", 9.99, "https://wefit-react-web-test.s3.amazonaws.com/viuva-negra.png"),
            Product(2, "Shang-chi", 30.99, "https://wefit-react-web-test.s3.amazonaws.com/shang-chi.png"),
            Product(3, "Homem Aranha", 29.9, "https://wefit-react-web-test.s3.amazonaws.com/spider-man.png"),
            Product(4, "Morbius", 1.5, "https://wefit-react-web-test.s3.amazonaws.com/morbius-1.png"),
            Product(5, "Batman", 21.9, "https://wefit-react-web-test.s3.amazonaws.com/the-batman.png"),
            Product(6, "Eternos", 17.9, "https://wefit-react-web-test.s3.amazonaws.com/eternals.png")
        )

        adapter.updateProducts(mockProducts) // Atualiza o adapter com os dados locais
    }
}

