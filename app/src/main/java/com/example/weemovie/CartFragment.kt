package com.example.weemovie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.CartAdapter
import com.example.weemovie.ViewModel.CartViewModel


class CartFragment : Fragment() {

    private lateinit var finalizeOrderButton: Button
    private lateinit var totalTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var emptyCartLayout: LinearLayout
    private lateinit var backToHomeButton: Button

    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        finalizeOrderButton = view.findViewById(R.id.finalizeOrderButton)
        totalTextView = view.findViewById(R.id.totalPriceText)
        recyclerView = view.findViewById(R.id.recyclerViewCart)
        emptyCartLayout = view.findViewById(R.id.emptyCartLayout)
        backToHomeButton = view.findViewById(R.id.backToHomeButton)

        // Configura o RecyclerView e o Adapter
        cartAdapter = CartAdapter(mutableListOf()) { product, quantity ->
            cartViewModel.updateProductQuantity(product, quantity)
        }
        recyclerView.adapter = cartAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observa as mudanças no carrinho para atualizar a interface
        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            updateCartView(cartItems)
            updateTotalPrice()
        }

        // Botão "Voltar à Home" para o layout vazio
        backToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_homeFragment)
        }

        // Botão "Finalizar Pedido"
        finalizeOrderButton.setOnClickListener {
            // Iniciar a tela de confirmação de pedido
            val intent = Intent(activity, OrderConfirmationActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun updateTotalPrice() {
        val total = cartViewModel.getTotalPrice()
        totalTextView.text = "TOTAL R$ %.2f".format(total)
    }

    private fun updateCartView(cartItems: MutableMap<Product, Int>) {
        if (cartItems.isEmpty()) {
            // Exibe o layout do carrinho vazio
            emptyCartLayout.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            finalizeOrderButton.visibility = View.GONE
        } else {
            // Exibe a RecyclerView e o botão "Finalizar Pedido"
            emptyCartLayout.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            finalizeOrderButton.visibility = View.VISIBLE
            cartAdapter.updateCartItems(cartItems.toList())
        }
    }
}



