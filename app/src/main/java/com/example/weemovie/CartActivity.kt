//import android.os.Bundle
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.weemovie.Adapter.CartAdapter
//import com.example.weemovie.R
//
//
//import androidx.activity.viewModels
//import com.example.weemovie.ViewModel.CartViewModel
//
//class CartActivity : AppCompatActivity() {
//
//    private val cartViewModel: CartViewModel by viewModels()
//    private lateinit var adapter: CartAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_cart)
//
//        val recyclerView: RecyclerView = findViewById(R.id.cartRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        adapter = CartAdapter(mutableListOf()) { product ->
//            cartViewModel.removeItem(product)
//        }
//        recyclerView.adapter = adapter
//
//        // observa a lista de itens no carrinho
//        cartViewModel.cartItems.observe(this) { items ->
//            adapter.updateCartItems(items)
//            updateTotalPrice()
//        }
//
//        // observa o preço total
//        cartViewModel.totalPrice.observe(this) { totalPrice ->
//            findViewById<TextView>(R.id.totalPrice).text = "Total: R$ $totalPrice"
//        }
//    }
//
//    private fun updateTotalPrice() {
//        val totalPriceTextView = findViewById<TextView>(R.id.totalPrice)
//        val totalPrice = cartViewModel.totalPrice.value ?: 0.0
//        totalPriceTextView.text = "Total: R$ $totalPrice"
//    }
//}
