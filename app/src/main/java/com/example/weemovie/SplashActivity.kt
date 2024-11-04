package com.example.weemovie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Aguarda um curto período para exibir a Splash Screen e inicia a LoadingActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoadingActivity::class.java))
            finish() // Fecha a SplashActivity
        }, 2000) // Aguarda 2 segundos, ajuste conforme necessário
    }
}
