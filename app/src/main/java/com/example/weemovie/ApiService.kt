package com.example.weemovie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import retrofit2.Call
import retrofit2.http.GET


data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String
)


data class ProductResponse(
    val products: List<Product>
)

interface ApiService {
    @GET("movies")
    fun getMovies(): Call<ProductResponse>
}

