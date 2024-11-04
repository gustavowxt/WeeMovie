package com.example.weemovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.ProductAdapter
import com.example.weemovie.ViewModel.CartViewModel
import com.google.android.material.snackbar.Snackbar

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

    private val cartViewModel: CartViewModel by activityViewModels()  // ViewModel compartilhada

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(context)

        productAdapter = ProductAdapter(products) { product ->
            cartViewModel.updateCart(product)  // Atualiza o carrinho diretamente no ViewModel

            // Exibe uma mensagem de feedback ao usuário
            Snackbar.make(
                view,
                if (cartViewModel.cartItems.value?.containsKey(product) == true) {
                    "${product.title} adicionado ao carrinho"
                } else {
                    "${product.title} removido ao carrinho"
                },
                Snackbar.LENGTH_SHORT
            ).show()
        }
        recyclerView.adapter = productAdapter

        return view
    }
}




