package com.example.weemovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.weemovie.Repository.MockProductRepository
import com.example.weemovie.Repository.ProductRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private val cartItems = mutableListOf<Product>()
    private val repository: ProductRepository = MockProductRepository() // Dados mockados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Configura o BottomNavigationView e o NavController
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        // Configura o BottomNavigationView com o NavController
        bottomNavigationView.setupWithNavController(navController)
    }

    // Atualiza o contador de itens no BottomNavigationView
    private fun updateCartBadge(count: Int) {
        val menuItem = bottomNavigationView.menu.findItem(R.id.cartFragment)
        menuItem.title = "Carrinho ($count)"
    }

    // Adiciona um item ao carrinho e exibe feedback
    fun addToCart(product: Product) {
        if (!cartItems.contains(product)) {
            cartItems.add(product)
            updateCartBadge(cartItems.size) // Atualiza o contador do carrinho

            // Exibe mensagem de feedback para o usuário
            Snackbar.make(
                findViewById(android.R.id.content),
                "${product.title} adicionado ao carrinho!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
