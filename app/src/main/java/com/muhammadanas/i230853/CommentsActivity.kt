package com.muhammadanas.i230853

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 06 · Comments
  Threaded comments with reactions and a reply bar. Opened from Comment on a Home post.
 */
class CommentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comments)

        // Pad the layout for the status bar, navigation bar AND the keyboard (ime),
        // so the reply bar is pushed up above the keyboard when typing.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Back arrow: return to the previous screen (Home)
        findViewById<ImageView>(R.id.iv_comments_back).setOnClickListener { finish() }
    }
}