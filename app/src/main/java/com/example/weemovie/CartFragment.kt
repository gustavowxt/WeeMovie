package com.example.weemovie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weemovie.Adapter.CartAdapter

class CartFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var finalizeOrderButton: Button
    private lateinit var totalTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(context)

        finalizeOrderButton = view.findViewById(R.id.finalizeOrderButton)
        totalTextView = view.findViewById(R.id.totalTextView)

        cartAdapter = CartAdapter(mutableListOf()) { product, quantity ->
            updateTotal()
        }
        recyclerView.adapter = cartAdapter

        finalizeOrderButton.setOnClickListener {
            // Lógica para finalizar pedido e ir para a tela de confirmação
            val intent = Intent(activity, OrderConfirmationActivity::class.java)
            startActivity(intent)
        }

        updateTotal()
        return view
    }

    private fun updateTotal() {
        val total = cartAdapter.getTotalPrice()
        totalTextView.text = "R$ %.2f".format(total)
    }

    fun updateCart(product: Product) {
        cartAdapter.updateCart(product)
        updateTotal()
    }
}
