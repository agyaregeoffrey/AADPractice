package com.gka.aadpractice.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.gka.aadpractice.R

class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler(mainLooper)
        handler.postDelayed({
            startActivity(Intent(this, LeaderBoardActivity::class.java))
            finish()
        }, 2000)

    }
}