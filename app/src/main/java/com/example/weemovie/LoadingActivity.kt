package com.example.weemovie

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // Simula o carregamento de dados com um atraso e, em seguida, inicia a MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Fecha a LoadingActivity
        }, 3000) // Aguarda 3 segundos para simular o carregamento, ajuste conforme necessário
    }
}