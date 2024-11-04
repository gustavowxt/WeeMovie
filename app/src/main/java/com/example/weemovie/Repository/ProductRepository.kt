package com.example.weemovie.Repository

import com.example.weemovie.Product

interface ProductRepository {
    fun getProducts(callback: (List<Product>) -> Unit)
}


