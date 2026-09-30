package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
  Stage 02 · Log in
  First screen after Splash.
  - "Create new account" opens Sign up.
  - "Log in" will open Home once Home is built in Stage 02 of Phase 02.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        // Padding the layout so content never sits under the status or navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // "Create new account" -> Sign up, using an explicit Intent.
        // No finish() here: Log in stays in the back stack, so Back on Sign up returns to it.
        findViewById<Button>(R.id.btn_create_account).setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}