package com.muhammadanas.i230853

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 12 · Your story
  Your shared story with viewers, hearts and actions. Opened from the Story editor.
 */
class YourStoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Dark screen, so the status bar and navigation bar icons are white
        enableEdgeToEdge(SystemBarStyle.dark(Color.TRANSPARENT), SystemBarStyle.dark(Color.TRANSPARENT))
        setContentView(R.layout.activity_your_story)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // X: close the story. Camera and editor already closed themselves,
        // so this goes straight back to where the story was started (Home, Create post or Profile).
        findViewById<View>(R.id.iv_your_story_close).setOnClickListener { finish() }
    }
}