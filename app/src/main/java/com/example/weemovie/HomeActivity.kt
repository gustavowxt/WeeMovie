package com.example.weemovie

import CartActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.ProductAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.seu.pacote.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        RetrofitInstance.api.getMovies().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    val products = response.body()?.products ?: emptyList()
                    adapter.updateProducts(products) // Atualiza a lista de produtos no adapter
                } else {
                    Log.e("HomeActivity", "Erro na resposta: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("HomeActivity", "Erro ao buscar produtos: ${t.message}")
                Toast.makeText(this@HomeActivity, "Não foi possível carregar os produtos. Tente novamente mais tarde.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

