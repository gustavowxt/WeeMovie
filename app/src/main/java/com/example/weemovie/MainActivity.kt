package com.example.weemovie

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.weemovie.ViewModel.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        // Observa as mudanças no carrinho para atualizar o contador
        cartViewModel.cartItems.observe(this, Observer { cartItems ->
            updateCartBadge(cartItems.size)
        })
    }

    private fun updateCartBadge(count: Int) {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val cartMenuItem = bottomNavigationView.menu.findItem(R.id.cartFragment)
        cartMenuItem.title = if (count > 0) "Carrinho ($count)" else "Carrinho"
    }
}

