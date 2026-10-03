package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
 SCREEN 15: PROFILE

*/

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Back arrow: close this screen and go back to where we came from
        findViewById<View>(R.id.iv_profile_back).setOnClickListener { finish() }

        // Search icon in the top bar
        findViewById<View>(R.id.iv_profile_search).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        // "Find friends": bring the Friends tab to the front instead of making a new copy
        findViewById<View>(R.id.tv_find_friends).setOnClickListener {
            val intent = Intent(this, FriendsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
    }
}