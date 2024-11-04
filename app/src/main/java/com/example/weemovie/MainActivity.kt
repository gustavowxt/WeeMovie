package com.example.weemovie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val cartItems = mutableMapOf<Product, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configura o NavController para o BottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)
    }

    fun updateCart(product: Product) {
        if (cartItems.containsKey(product)) {
            cartItems.remove(product)
            Toast.makeText(this, "${product.title} removido do carrinho", Toast.LENGTH_SHORT).show()
        } else {
            cartItems[product] = 1
            Toast.makeText(this, "${product.title} adicionado ao carrinho", Toast.LENGTH_SHORT).show()
        }
        updateCartBadge()
    }

    fun getCartItems(): Map<Product, Int> = cartItems

    private fun updateCartBadge() {
        val cartCount = cartItems.size
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val cartMenuItem = bottomNavigationView.menu.findItem(R.id.cartFragment)
        cartMenuItem.title = if (cartCount > 0) "Carrinho ($cartCount)" else "Carrinho"
    }
}

