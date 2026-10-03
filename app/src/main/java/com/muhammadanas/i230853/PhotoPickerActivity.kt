package com.muhammadanas.i230853

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 08 · Photo picker
  Pick photos from a grid; the chosen one shows big at the top. Opened from Create post.
 */
class PhotoPickerActivity : AppCompatActivity() {

    // The 16 photos in the grid, row by row
    private val tileIds = intArrayOf(
        R.id.rl_pick_1, R.id.rl_pick_2, R.id.rl_pick_3, R.id.rl_pick_4,
        R.id.rl_pick_5, R.id.rl_pick_6, R.id.rl_pick_7, R.id.rl_pick_8,
        R.id.rl_pick_9, R.id.rl_pick_10, R.id.rl_pick_11, R.id.rl_pick_12,
        R.id.rl_pick_13, R.id.rl_pick_14, R.id.rl_pick_15, R.id.rl_pick_16
    )

    // The teal frame on each photo (only the one in the preview is visible)
    private val frameIds = intArrayOf(
        R.id.v_pick_frame_1, R.id.v_pick_frame_2, R.id.v_pick_frame_3, R.id.v_pick_frame_4,
        R.id.v_pick_frame_5, R.id.v_pick_frame_6, R.id.v_pick_frame_7, R.id.v_pick_frame_8,
        R.id.v_pick_frame_9, R.id.v_pick_frame_10, R.id.v_pick_frame_11, R.id.v_pick_frame_12,
        R.id.v_pick_frame_13, R.id.v_pick_frame_14, R.id.v_pick_frame_15, R.id.v_pick_frame_16
    )

    // What each photo shows in the big preview (sand photos use the wide sand picture)
    private val previewArt = intArrayOf(
        R.drawable.art_preview_sand, R.drawable.art_photo_sea, R.drawable.art_photo_dusk, R.drawable.art_photo_sky,
        R.drawable.art_photo_meadow, R.drawable.art_photo_rose, R.drawable.art_photo_night, R.drawable.art_photo_sea,
        R.drawable.art_photo_dusk, R.drawable.art_preview_sand, R.drawable.art_photo_meadow, R.drawable.art_photo_sky,
        R.drawable.art_photo_rose, R.drawable.art_photo_night, R.drawable.art_photo_sky, R.drawable.art_preview_sand
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_photo_picker)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cancel and Next both go back to Create post
        findViewById<View>(R.id.tv_picker_cancel).setOnClickListener { finish() }
        findViewById<View>(R.id.tv_picker_next).setOnClickListener { finish() }

        // Tapping a photo shows it in the big preview and moves the teal frame onto it
        val preview = findViewById<ImageView>(R.id.iv_picker_preview)
        for (i in tileIds.indices) {
            findViewById<View>(tileIds[i]).setOnClickListener {
                preview.setImageResource(previewArt[i])
                for (frameId in frameIds) {
                    findViewById<View>(frameId).visibility = View.GONE
                }
                findViewById<View>(frameIds[i]).visibility = View.VISIBLE
            }
        }
    }
}