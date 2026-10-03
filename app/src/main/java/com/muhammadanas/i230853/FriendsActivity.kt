package com.muhammadanas.i230853

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 14 · Friends
  Friend requests and suggestions. Opened from the Friends tab.
 */

class FriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_friends)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Top tab bar + header search button
        TabBar.setup(this, FriendsActivity::class.java)

        // Tapping a friend request's picture or name opens that person's profile.
        // The name, initials and circle colour are read from the request and sent along.
        val avatarIds = intArrayOf(R.id.tv_request_avatar_1, R.id.tv_request_avatar_2, R.id.tv_request_avatar_3, R.id.tv_request_avatar_4)
        val nameIds = intArrayOf(R.id.tv_request_name_1, R.id.tv_request_name_2, R.id.tv_request_name_3, R.id.tv_request_name_4)
        for (i in avatarIds.indices) {
            val avatar = findViewById<TextView>(avatarIds[i])
            val name = findViewById<TextView>(nameIds[i])
            val openProfile = View.OnClickListener {
                val intent = Intent(this, OtherProfileActivity::class.java)
                intent.putExtra(OtherProfileActivity.EXTRA_NAME, name.text.toString())
                intent.putExtra(OtherProfileActivity.EXTRA_INITIALS, avatar.text.toString())
                intent.putExtra(OtherProfileActivity.EXTRA_AVATAR_COLOR, avatar.backgroundTintList?.defaultColor ?: Color.GRAY)
                startActivity(intent)
            }
            avatar.setOnClickListener(openProfile)
            name.setOnClickListener(openProfile)
        }
    }
}


