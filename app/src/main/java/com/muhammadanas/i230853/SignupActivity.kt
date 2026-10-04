package com.muhammadanas.i230853

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 03 · Sign up
  New account form with gender options. Opened from Log in's Create new account.
 */
class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        // Pad the layout so content never sits under the status or navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Back arrow: close Sign up; Log in is underneath in the back stack
        findViewById<ImageView>(R.id.iv_signup_back).setOnClickListener { finish() }

        // "Already have an account? Log in": return to the existing Log in screen
        findViewById<TextView>(R.id.tv_signup_log_in).setOnClickListener { finish() }

        // "Create account" -> Home.
        // NEW_TASK + CLEAR_TASK remove Log in and Sign up from the back stack so Back on Home leaves the app instead of returning to the form.
        findViewById<Button>(R.id.btn_signup_create).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // Gender: make only the selected option's text bold, as in the design
        findViewById<RadioGroup>(R.id.rg_gender).setOnCheckedChangeListener { group, checkedId ->
            for (i in 0 until group.childCount) {
                val option = group.getChildAt(i) as RadioButton
                val style = if (option.id == checkedId) Typeface.BOLD else Typeface.NORMAL
                option.setTypeface(null, style)
            }
        }
    }
}