package com.muhammadanas.i230853

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 09 · Camera
  Story camera with viewfinder, shutter and modes. Opened from Create story, Create post and Profile.
 */
class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Dark screen, so the status bar and navigation bar icons are white
        enableEdgeToEdge(SystemBarStyle.dark(Color.TRANSPARENT), SystemBarStyle.dark(Color.TRANSPARENT))
        setContentView(R.layout.activity_camera)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // X: close the camera
        findViewById<View>(R.id.iv_camera_close).setOnClickListener { finish() }

        // Gallery thumbnail opens the Photo picker
        findViewById<View>(R.id.iv_camera_gallery).setOnClickListener {
            startActivity(Intent(this, PhotoPickerActivity::class.java))
        }

        // Modes: tapping one makes it white with a line under it, the rest go grey
        val modeIds = intArrayOf(R.id.ll_mode_text, R.id.ll_mode_live, R.id.ll_mode_story, R.id.ll_mode_post, R.id.ll_mode_boomerang)
        val modeTextIds = intArrayOf(R.id.tv_mode_text, R.id.tv_mode_live, R.id.tv_mode_story, R.id.tv_mode_post, R.id.tv_mode_boomerang)
        val modeLineIds = intArrayOf(R.id.v_mode_line_text, R.id.v_mode_line_live, R.id.v_mode_line_story, R.id.v_mode_line_post, R.id.v_mode_line_boomerang)
        for (i in modeIds.indices) {
            findViewById<View>(modeIds[i]).setOnClickListener {
                for (j in modeIds.indices) {
                    val selected = (j == i)
                    val color = if (selected) R.color.white else R.color.camera_mode_grey
                    findViewById<TextView>(modeTextIds[j]).setTextColor(ContextCompat.getColor(this, color))
                    findViewById<View>(modeLineIds[j]).visibility = if (selected) View.VISIBLE else View.INVISIBLE
                }
            }
        }
    }
}