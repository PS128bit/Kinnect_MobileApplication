package com.muhammadanas.i230853

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 13 · Search
  Opened from the search button on any main tab screen. Back returns there.
 */
class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)

        // Pad for the system bars and the keyboard
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Back arrow: return to the screen Search was opened from
        findViewById<ImageView>(R.id.iv_search_back).setOnClickListener { finish() }

        // Clear (X): empty the search box
        val searchBox = findViewById<EditText>(R.id.et_search)
        findViewById<ImageView>(R.id.iv_search_clear).setOnClickListener {
            searchBox.setText("")
        }
    }
}