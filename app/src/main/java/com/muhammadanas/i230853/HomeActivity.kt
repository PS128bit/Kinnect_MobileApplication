package com.muhammadanas.i230853

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.view.View

/*
  SCREEN 04 · Home feed
  First main screen after logging in. The top tabs switch to the other main screens.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // Pad the layout so content never sits under the status or navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Top tab bar
        TabBar.setup(this, HomeActivity::class.java)


        // "Comment" button and "86 comments · 12 shares" -> Comments
        findViewById<View>(R.id.ll_action_comment).setOnClickListener { openComments() }
        findViewById<View>(R.id.tv_post_counts).setOnClickListener { openComments() }


        // "Like" button -> Reaction picker
        findViewById<View>(R.id.ll_action_like).setOnClickListener {
            startActivity(Intent(this, ReactionPickerActivity::class.java))
        }
    }

    private fun openComments() {
        startActivity(Intent(this, CommentsActivity::class.java))
    }

}