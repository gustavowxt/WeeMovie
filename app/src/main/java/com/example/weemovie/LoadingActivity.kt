package com.example.weemovie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)


        CoroutineScope(Dispatchers.Main).launch {
            delay(3000) // O tempo que o loading vai ficar na tela
            startActivity(Intent(this@LoadingActivity, HomeActivity::class.java))
            finish()
        }
    }
}
