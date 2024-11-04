package com.example.weemovie.Repository

import com.example.weemovie.Product

class MockProductRepository : ProductRepository {
    override fun getProducts(callback: (List<Product>) -> Unit) {
        val mockProducts = listOf(
            Product(1, "Viúva Negra", 9.99, "https://wefit-react-web-test.s3.amazonaws.com/viuva-negra.png"),
            Product(2, "Shang-chi", 30.99, "https://wefit-react-web-test.s3.amazonaws.com/shang-chi.png"),
            Product(3, "Homem Aranha", 29.9, "https://wefit-react-web-test.s3.amazonaws.com/spider-man.png"),
            Product(4, "Morbius", 1.5, "https://wefit-react-web-test.s3.amazonaws.com/morbius-1.png"),
            Product(5, "Batman", 21.9, "https://wefit-react-web-test.s3.amazonaws.com/the-batman.png"),
            Product(6, "Eternos", 17.9, "https://wefit-react-web-test.s3.amazonaws.com/eternals.png")
        )
        callback(mockProducts) // Retorna os dados mockados da API local
    }
}