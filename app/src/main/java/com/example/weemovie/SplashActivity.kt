package com.example.weemovie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // o tempo de exibição da splash em milisegundos
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, LoadingActivity::class.java))
            finish()
        }
    }
}
