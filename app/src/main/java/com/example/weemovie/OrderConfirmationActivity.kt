package com.example.weemovie

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OrderConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        val backToHomeButton = findViewById<Button>(R.id.backToHomeButton)
        backToHomeButton.setOnClickListener {
            finish()
        }
    }
}
