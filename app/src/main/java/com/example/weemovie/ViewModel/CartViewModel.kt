package com.example.weemovie.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weemovie.Product

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableMap<Product, Int>>(mutableMapOf())
    val cartItems: LiveData<MutableMap<Product, Int>> = _cartItems

    // Função para alternar a presença de um produto no carrinho
    fun toggleCartProduct(product: Product): Boolean {
        val currentItems = _cartItems.value ?: mutableMapOf()

        // Se o produto já está no carrinho, remove e retorna false
        if (currentItems.containsKey(product)) {
            currentItems.remove(product)
            _cartItems.value = currentItems
            return false // Filme removido
        } else {
            // Se o produto não está no carrinho, adiciona e retorna true
            currentItems[product] = 1
            _cartItems.value = currentItems
            return true // Filme adicionado
        }
    }

    // Função para obter o numero de itens no carrinho
    fun getCartItemCount(): Int = _cartItems.value?.size ?: 0

    // Atualiza a quantidade de um filme específico no carrinho
    fun updateProductQuantity(product: Product, quantity: Int) {
        val currentItems = _cartItems.value ?: mutableMapOf()

        if (quantity > 0) {
            currentItems[product] = quantity
        } else {
            currentItems.remove(product)
        }

        _cartItems.value = currentItems
    }

    // Obtem o preço total dos filmes no carrinho
    fun getTotalPrice(): Double {
        return _cartItems.value?.entries?.sumOf { (product, quantity) ->
            product.price * quantity
        } ?: 0.0
    }

    // Função para verificar se o filme está no carrinho
    fun isProductInCart(product: Product): Boolean {
        return _cartItems.value?.containsKey(product) == true
    }

    // Função para obter a quantidade de um filme específico no carrinho
    fun getProductQuantity(product: Product): Int {
        return _cartItems.value?.get(product) ?: 0
    }
}