package com.example.weemovie.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weemovie.Product
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<Product>>(mutableListOf())
    val cartItems: LiveData<MutableList<Product>> = _cartItems

    private val _totalPrice = MutableLiveData<Double>(0.0)
    val totalPrice: LiveData<Double> = _totalPrice

    fun addItem(product: Product) {
        _cartItems.value?.add(product)
        calculateTotalPrice()
    }

    fun removeItem(product: Product) {
        _cartItems.value?.remove(product)
        calculateTotalPrice()
    }

    private fun calculateTotalPrice() {
        _totalPrice.value = _cartItems.value?.sumOf { it.price } ?: 0.0
    }
}



class CartRepository(private val context: Context) {
    private val sharedPrefs = context.getSharedPreferences("cart_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveCart(cartItems: List<Product>) {
        val json = gson.toJson(cartItems)
        sharedPrefs.edit().putString("cart_items", json).apply()
    }

    fun loadCart(): List<Product> {
        val json = sharedPrefs.getString("cart_items", null) ?: return emptyList()
        val type = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(json, type)
    }
}
