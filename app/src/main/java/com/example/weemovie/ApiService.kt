package com.example.weemovie

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movies")
    fun getMovies(): Call<ProductResponse>
}

data class ProductResponse(
    val products: List<Product>
)


data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String
)





