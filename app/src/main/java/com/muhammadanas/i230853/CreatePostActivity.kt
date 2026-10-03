package com.muhammadanas.i230853

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 07 · Create post
  Write a post, pick a text colour, and choose what to add. Opened from Home's composer.
 */
class CreatePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_post)
        // ime() too, so the screen moves up above the keyboard
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // X: close without posting
        findViewById<View>(R.id.iv_create_close).setOnClickListener { finish() }

        // Post: close and go back to Home
        findViewById<View>(R.id.tv_create_post_button).setOnClickListener { finish() }

        // Colour squares: tapping one changes the post text to that colour.
        // "Aa" puts it back to the normal dark text.
        val postText = findViewById<EditText>(R.id.et_create_text)
        val swatchIds = intArrayOf(
            R.id.tv_swatch_plain, R.id.v_swatch_1, R.id.v_swatch_2, R.id.v_swatch_3,
            R.id.v_swatch_4, R.id.v_swatch_5, R.id.v_swatch_6, R.id.v_swatch_7
        )
        val swatchColors = intArrayOf(
            R.color.text_primary, R.color.kinnect_teal, R.color.accent_orange, R.color.avatar_indigo,
            R.color.avatar_green, R.color.reaction_yellow, R.color.avatar_purple, R.color.text_primary
        )
        for (i in swatchIds.indices) {
            findViewById<View>(swatchIds[i]).setOnClickListener {
                postText.setTextColor(ContextCompat.getColor(this, swatchColors[i]))
            }
        }

        // Photo/video row opens the Photo picker
        findViewById<View>(R.id.ll_option_photo).setOnClickListener {
            startActivity(Intent(this, PhotoPickerActivity::class.java))
        }

        // Camera row opens the Camera
        findViewById<View>(R.id.ll_option_camera).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

    }
}