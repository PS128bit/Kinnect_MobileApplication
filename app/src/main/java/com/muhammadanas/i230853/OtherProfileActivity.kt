package com.muhammadanas.i230853

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 17 · Other profile
  Design: someone else's profile. Night cover with a moon, round picture with a teal ring
  in the middle, name, friend and mutual counts, bio, Add friend / Message / More buttons,
  mutual friends row, details (work, city, joined) and three photos.
  Opened from: Search (Omar Farooq), Friends (friend requests), Profile (friend tiles).
  The name, initials and circle color arrive as Intent extras; with no extras it shows Omar Farooq.
 */
class OtherProfileActivity : AppCompatActivity() {

    companion object {
        // Keys for the data other screens send here
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_INITIALS = "extra_initials"
        const val EXTRA_AVATAR_COLOR = "extra_avatar_color"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_other_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Read who we are showing. If nothing was sent, show Omar Farooq like the design.
        val name = intent.getStringExtra(EXTRA_NAME) ?: getString(R.string.name_omar_farooq)
        val initials = intent.getStringExtra(EXTRA_INITIALS) ?: getString(R.string.initials_of)
        val color = intent.getIntExtra(EXTRA_AVATAR_COLOR, ContextCompat.getColor(this, R.color.avatar_green))

        findViewById<TextView>(R.id.tv_other_top_name).text = name
        findViewById<TextView>(R.id.tv_other_name).text = name
        val avatar = findViewById<TextView>(R.id.tv_other_avatar)
        avatar.text = initials
        avatar.backgroundTintList = ColorStateList.valueOf(color)

        // Back arrow: close this screen
        findViewById<View>(R.id.iv_other_back).setOnClickListener { finish() }

        // Search icon in the top bar
        findViewById<View>(R.id.iv_other_search).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        // Add friend: the button text changes to "Request sent"
        val tvAddFriend = findViewById<TextView>(R.id.tv_add_friend)
        findViewById<View>(R.id.ll_add_friend).setOnClickListener {
            tvAddFriend.setText(R.string.other_request_sent)
        }
    }
}