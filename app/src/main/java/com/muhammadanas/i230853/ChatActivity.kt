package com.muhammadanas.i230853

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 21 · Chat
  One conversation with messages, a reaction and a photo. Opened from Chats and Other profile.
 */
class ChatActivity : AppCompatActivity() {

    companion object {
        // Keys for the person this chat is with
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_INITIALS = "extra_initials"
        const val EXTRA_AVATAR_COLOR = "extra_avatar_color"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)
        // ime() too, so the message bar moves up above the keyboard
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Who is this chat with? If nothing was sent, it's Aisha Khan like the design.
        val name = intent.getStringExtra(EXTRA_NAME) ?: getString(R.string.name_aisha_khan)
        val initials = intent.getStringExtra(EXTRA_INITIALS) ?: getString(R.string.initials_ak)
        val color = intent.getIntExtra(EXTRA_AVATAR_COLOR, ContextCompat.getColor(this, R.color.avatar_maroon))

        // Put their name in the top bar and the intro
        findViewById<TextView>(R.id.tv_chat_header_name).text = name
        findViewById<TextView>(R.id.tv_chat_intro_name).text = name

        // Put their initials and colour on all four pictures
        val avatarIds = intArrayOf(
            R.id.tv_chat_header_avatar, R.id.tv_chat_intro_avatar,
            R.id.tv_chat_small_avatar_1, R.id.tv_chat_small_avatar_2
        )
        for (id in avatarIds) {
            val avatar = findViewById<TextView>(id)
            avatar.text = initials
            avatar.backgroundTintList = ColorStateList.valueOf(color)
        }

        // Back arrow: return to Chats (or wherever we came from)
        findViewById<View>(R.id.iv_chat_back).setOnClickListener { finish() }

        // Phone icon: start a voice call with this person
        findViewById<View>(R.id.iv_chat_call).setOnClickListener {
            val call = Intent(this, VoiceCallActivity::class.java)
            call.putExtra(VoiceCallActivity.EXTRA_NAME, name)
            call.putExtra(VoiceCallActivity.EXTRA_INITIALS, initials)
            call.putExtra(VoiceCallActivity.EXTRA_AVATAR_COLOR, color)
            startActivity(call)
        }
    }
}