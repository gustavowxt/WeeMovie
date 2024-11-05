package com.example.weemovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.ProductAdapter
import com.example.weemovie.ViewModel.CartViewModel

class HomeFragment : Fragment() {

    private lateinit var productAdapter: ProductAdapter
    private val products = listOf(
        Product(1, "Viúva Negra", 9.99, "https://wefit-react-web-test.s3.amazonaws.com/viuva-negra.png"),
        Product(2, "Shang-chi", 30.99, "https://wefit-react-web-test.s3.amazonaws.com/shang-chi.png"),
        Product(3, "Homem Aranha", 29.9, "https://wefit-react-web-test.s3.amazonaws.com/spider-man.png"),
        Product(4, "Morbius", 1.5, "https://wefit-react-web-test.s3.amazonaws.com/morbius-1.png"),
        Product(5, "Batman", 21.9, "https://wefit-react-web-test.s3.amazonaws.com/the-batman.png"),
        Product(6, "Eternos", 17.9, "https://wefit-react-web-test.s3.amazonaws.com/eternals.png")
    )

    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(context)

        productAdapter = ProductAdapter(
            products,
            onProductToggle = { product -> cartViewModel.toggleCartProduct(product) },
            isProductInCart = { product -> cartViewModel.isProductInCart(product) },
            getProductQuantity = { product -> cartViewModel.getProductQuantity(product) }
        )

        recyclerView.adapter = productAdapter

        return view
    }
}





