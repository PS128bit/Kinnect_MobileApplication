package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  01 · Splash
  First screen of the app (launcher activity). Shows the Kinnect logo and
  wordmark on teal. Opening Log in automatically is added in the next step in Stage 2 of Phase 1 my nigga.
 */

class SplashActivity : AppCompatActivity() {

    // How long the Splash stays on screen, in milliseconds (2000 ms = 2 seconds)
    private val splashDelay = 2500L

    // Handler on the main (UI) thread, used to run code after the delay
    private val handler = Handler(Looper.getMainLooper())

    // What happens when the delay ends: open Log in with an explicit Intent (Lecture 05),
    // then finish() Splash so pressing Back on Log in closes the app
    // instead of returning to the Splash screen.
    private val openLogin = Runnable {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // This pads the layout so the text never sits underneath them while the teal background still fills the whole screen.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Start the countdown to Log in
        handler.postDelayed(openLogin, splashDelay)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Lifecycle cleanup : if the user leaves Splash before the 2 seconds and 500 milliseconds(I love numbers and Maths) are up, cancel the countdown so Log in doesn't pop up later.
        handler.removeCallbacks(openLogin)
    }
}