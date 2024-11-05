package com.example.weemovie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.CartAdapter
import com.example.weemovie.ViewModel.CartViewModel

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var finalizeOrderButton: Button
    private lateinit var totalTextView: TextView

    private val cartViewModel: CartViewModel by activityViewModels()  // ViewModel compartilhada

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(context)

        finalizeOrderButton = view.findViewById(R.id.finalizeOrderButton)
        totalTextView = view.findViewById(R.id.totalTextView)

        // Inicia o adapter com uma lista vazia (será preenchida ao observar o ViewModel)
        cartAdapter = CartAdapter(mutableListOf()) { product, _ ->
            cartViewModel.toggleCartProduct(product)  // Altera o estado do produto no carrinho ao remover
            updateTotal()
        }
        recyclerView.adapter = cartAdapter

        // Observa as mudanças nos itens do carrinho
        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.updateCartItems(cartItems)  // Atualiza o adapter com os itens do carrinho
            updateTotal()  // Atualiza o total
        }

        finalizeOrderButton.setOnClickListener {
            val intent = Intent(activity, OrderConfirmationActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun updateTotal() {
        val total = cartAdapter.getTotalPrice()
        totalTextView.text = "R$ %.2f".format(total)
    }
}