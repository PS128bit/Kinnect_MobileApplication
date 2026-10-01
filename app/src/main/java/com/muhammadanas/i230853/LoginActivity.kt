package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
  SCREEN 02 · Log in
  First screen after Splash.
  - "Create new account" opens Sign up.
  - "Log in" will open Home once Home is built in Stage 02 of Phase 02.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Pad the layout so content never sits under the status or navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // "Log in" -> Home
        findViewById<Button>(R.id.btn_log_in).setOnClickListener { openHome() }

        // "Tap to log in" on the Recent login card -> Home as well
        findViewById<RelativeLayout>(R.id.rl_recent_login).setOnClickListener { openHome() }

        // "Create new account" -> Sign up. No finish(): Back on Sign up returns here.
        findViewById<Button>(R.id.btn_create_account).setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    // Opens Home and closes Log in, so Back on Home leaves the app instead of returning to the login form (normal social-app behaviour).
    private fun openHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}