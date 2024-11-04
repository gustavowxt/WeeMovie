package com.example.weemovie.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weemovie.Product

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableMap<Product, Int>>(mutableMapOf())
    val cartItems: LiveData<MutableMap<Product, Int>> = _cartItems

    // Função para adicionar ou remover produtos
    fun updateCart(product: Product) {
        val currentItems = _cartItems.value ?: mutableMapOf()
        if (currentItems.containsKey(product)) {
            currentItems.remove(product)
        } else {
            currentItems[product] = 1
        }
        _cartItems.value = currentItems
    }

    // Função para obter o número de itens no carrinho
    fun getCartItemCount(): Int = _cartItems.value?.size ?: 0
}
