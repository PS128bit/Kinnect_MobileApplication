package com.muhammadanas.i230853

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 11 · Story viewer
  Opened from a story card on Home. Home sends the person's details with putExtra,
  so this one screen can show anyone's story. Close (X) returns to Home.
 */
class StoryViewerActivity : AppCompatActivity() {

    companion object {
        // Keys for the data sent from Home with the Intent
        const val EXTRA_NAME = "story_name"
        const val EXTRA_INITIALS = "story_initials"
        const val EXTRA_AVATAR_COLOR = "story_avatar_color"
        const val EXTRA_PHOTO = "story_photo"
        const val EXTRA_SHOW_CAPTION = "story_show_caption"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Dark screen: ask for light (white) status and navigation bar icons
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        setContentView(R.layout.activity_story_viewer)

        // Pad for the system bars and the keyboard (when typing a message)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Read the story details sent by Home (getStringExtra / getIntExtra).
        // If nothing was sent, the layout's defaults (Omar Farooq) are kept.
        intent.getStringExtra(EXTRA_NAME)?.let {
            findViewById<TextView>(R.id.tv_story_name).text = it
        }
        intent.getStringExtra(EXTRA_INITIALS)?.let {
            findViewById<TextView>(R.id.tv_story_avatar).text = it
        }
        val avatarColor = intent.getIntExtra(EXTRA_AVATAR_COLOR, R.color.avatar_green)
        findViewById<TextView>(R.id.tv_story_avatar).backgroundTintList =
            ContextCompat.getColorStateList(this, avatarColor)

        val photo = intent.getIntExtra(EXTRA_PHOTO, R.drawable.img_story_1)
        findViewById<ImageView>(R.id.iv_story_photo).setImageResource(photo)

        // Only Omar's story has the "Karachi nights" sticker
        val showCaption = intent.getBooleanExtra(EXTRA_SHOW_CAPTION, true)
        findViewById<View>(R.id.tv_story_caption).visibility =
            if (showCaption) View.VISIBLE else View.GONE

        // Close (X) returns to Home
        findViewById<ImageView>(R.id.iv_story_close).setOnClickListener { finish() }
    }
}