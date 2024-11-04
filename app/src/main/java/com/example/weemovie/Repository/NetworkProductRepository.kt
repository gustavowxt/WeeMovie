package com.example.weemovie.Repository


import com.example.weemovie.Product
import com.example.weemovie.ProductResponse
import com.seu.pacote.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NetworkProductRepository : ProductRepository {
    override fun getProducts(callback: (List<Product>) -> Unit) {
        RetrofitInstance.api.getMovies().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    val products = response.body()?.products ?: emptyList()
                    callback(products)
                } else {
                    callback(emptyList()) // Caso de resposta com erro
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                callback(emptyList()) // Em caso de falha de rede
            }
        })
    }
}

