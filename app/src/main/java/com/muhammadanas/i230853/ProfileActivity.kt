package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
 SCREEN 15: PROFILE
*/

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvTopName: TextView
    private lateinit var tvName: TextView
    private lateinit var tvBio: TextView

    // Opens Edit profile and waits for its answer.
    // If the user pressed Save, show the new name and bio here.
    private val editProfileLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val newName = data?.getStringExtra(EditProfileActivity.EXTRA_NAME)
                if (newName != null) {
                    tvTopName.text = newName
                    tvName.text = newName
                }
                val newBio = data?.getStringExtra(EditProfileActivity.EXTRA_BIO)
                if (newBio != null) {
                    tvBio.text = newBio
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTopName = findViewById(R.id.tv_profile_top_name)
        tvName = findViewById(R.id.tv_profile_name)
        tvBio = findViewById(R.id.tv_profile_bio)

        // Back arrow: close this screen and go back to where we came from
        findViewById<View>(R.id.iv_profile_back).setOnClickListener { finish() }

        // Search icon in the top bar
        findViewById<View>(R.id.iv_profile_search).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        // Edit profile button
        findViewById<View>(R.id.ll_edit_profile).setOnClickListener {
            editProfileLauncher.launch(Intent(this, EditProfileActivity::class.java))
        }

        // "Find friends": bring the Friends tab to the front instead of making a new copy
        findViewById<View>(R.id.tv_find_friends).setOnClickListener {
            val intent = Intent(this, FriendsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
    }
}