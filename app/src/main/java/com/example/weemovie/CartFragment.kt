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

    private lateinit var finalizeOrderButton: Button
    private lateinit var totalTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter

    private val cartViewModel: CartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        // Inicializa as views
        initializeViews(view)

        // Configura o RecyclerView e o Adapter
        setupRecyclerView(view) // Passa a view inflada como parâmetro

        // Observa as mudanças no carrinho para atualizar a interface
        observeCartItems()

        // Lida com a finalização do pedido
        setupFinalizeOrderButton()

        return view
    }

    private fun initializeViews(view: View) {
        finalizeOrderButton = view.findViewById(R.id.finalizeOrderButton)
        totalTextView = view.findViewById(R.id.totalPriceText)
    }

    private fun setupRecyclerView(view: View) { // Adiciona `view` como parâmetro
        recyclerView = view.findViewById(R.id.recyclerViewCart) // Usa a view passada

        // Passa uma função lambda que utiliza o cartViewModel para manipular a quantidade de produtos
        cartAdapter = CartAdapter(mutableListOf()) { product, newQuantity ->
            cartViewModel.updateProductQuantity(product, newQuantity)
            updateTotalPrice()  // Atualiza o total quando a quantidade é alterada
        }

        recyclerView.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeCartItems() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.updateCartItems(cartItems.toList())  // Converte para List<Pair<Product, Int>>
            updateTotalPrice()
        }
    }

    private fun setupFinalizeOrderButton() {
        finalizeOrderButton.setOnClickListener {
            // Inicia a tela de confirmação de pedido
            val intent = Intent(activity, OrderConfirmationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateTotalPrice() {
        val total = cartViewModel.getTotalPrice()
        totalTextView.text = "TOTAL R$ %.2f".format(total)
    }
}


