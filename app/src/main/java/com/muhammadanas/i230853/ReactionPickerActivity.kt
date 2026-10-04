package com.muhammadanas.i230853

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 05 · Reaction picker
  Lina's post dimmed with the reaction faces on top. Opened from Like on a Home post.
 */
class ReactionPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reaction_picker)

        // Pad the layout so content never sits under the status or navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Back arrow returns to Home
        findViewById<View>(R.id.iv_post_back).setOnClickListener { finish() }
    }

    override fun onStart() {
        super.onStart()

        // The dim layer and the faces only exist in the final layout, so look them up safely:
        // findViewById returns null if a view is not in the current layout.
        // Tapping the dim layer or choosing any reaction closes the picker.
        val closeIds = listOf(
            R.id.v_scrim,
            R.id.iv_react_like, R.id.iv_react_love, R.id.iv_react_haha,
            R.id.iv_react_wow, R.id.iv_react_sad, R.id.iv_react_angry
        )
        for (id in closeIds) {
            findViewById<View>(id)?.setOnClickListener { finish() }
        }
    }
}