package com.muhammadanas.i230853

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

/*
  SCREEN 22 · Voice call
  Ongoing call with a running timer and call controls. Opened from the phone icon in a Chat.
 */
class VoiceCallActivity : AppCompatActivity() {

    companion object {
        // Keys for the person being called
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_INITIALS = "extra_initials"
        const val EXTRA_AVATAR_COLOR = "extra_avatar_color"
    }

    private val handler = Handler(Looper.getMainLooper())
    private var seconds = 192          // the design shows 03:12
    private lateinit var tvTimer: TextView

    // Adds one second, shows it as mm:ss, then runs itself again after 1 second
    private val tick = object : Runnable {
        override fun run() {
            seconds++
            tvTimer.text = String.format(Locale.US, "%02d:%02d", seconds / 60, seconds % 60)
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Dark teal screen, so the status bar and navigation bar icons are white
        enableEdgeToEdge(SystemBarStyle.dark(Color.TRANSPARENT), SystemBarStyle.dark(Color.TRANSPARENT))
        setContentView(R.layout.activity_voice_call)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTimer = findViewById(R.id.tv_call_timer)

        // Calling someone from a Chat? Show their name, initials and colour, and hide the heart.
        // With no extras it shows "Ammi" like the design.
        val name = intent.getStringExtra(EXTRA_NAME)
        if (name != null) {
            findViewById<TextView>(R.id.tv_call_name).text = name
            val avatar = findViewById<TextView>(R.id.tv_call_avatar)
            avatar.text = intent.getStringExtra(EXTRA_INITIALS)
            avatar.backgroundTintList = ColorStateList.valueOf(intent.getIntExtra(EXTRA_AVATAR_COLOR, Color.GRAY))
            findViewById<View>(R.id.iv_call_heart).visibility = View.GONE
        }

        // Red button: end the call and go back to the chat
        findViewById<View>(R.id.iv_call_end).setOnClickListener { finish() }

        // Mute: tap to dim the mic button, tap again to undo
        val mute = findViewById<View>(R.id.iv_call_mute)
        mute.setOnClickListener {
            mute.alpha = if (mute.alpha == 1f) 0.5f else 1f
        }
    }

    // Timer runs only while the screen is visible
    override fun onStart() {
        super.onStart()
        handler.postDelayed(tick, 1000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(tick)
    }
}