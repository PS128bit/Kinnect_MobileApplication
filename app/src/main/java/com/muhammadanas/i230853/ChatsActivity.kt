package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 20 · Chats
  Notes row and the list of conversations. Opened from the Chats button on any tab.
 */
class ChatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chats)
        // ime() too, so the screen moves up above the keyboard when searching
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Back arrow: return to the tab we came from
        findViewById<View>(R.id.iv_chats_back).setOnClickListener { finish() }

        // Each chat row opens the Chat screen with that person's name, initials and colour
        val rowIds = intArrayOf(R.id.ll_chat_1, R.id.ll_chat_2, R.id.ll_chat_3, R.id.ll_chat_4, R.id.ll_chat_5, R.id.ll_chat_6)
        val names = intArrayOf(
            R.string.name_aisha_khan, R.string.chats_design_crew, R.string.name_lina_marsh,
            R.string.name_omar_farooq, R.string.name_bilal_ahmed, R.string.call_name_ammi
        )
        val initials = intArrayOf(
            R.string.initials_ak, R.string.initials_dc, R.string.initials_lm,
            R.string.initials_of, R.string.initials_ba, R.string.initials_a
        )
        val colors = intArrayOf(
            R.color.avatar_maroon, R.color.avatar_olive, R.color.avatar_purple,
            R.color.avatar_green, R.color.avatar_purple, R.color.avatar_green
        )
        for (i in rowIds.indices) {
            findViewById<View>(rowIds[i]).setOnClickListener {
                val chat = Intent(this, ChatActivity::class.java)
                chat.putExtra(ChatActivity.EXTRA_NAME, getString(names[i]))
                chat.putExtra(ChatActivity.EXTRA_INITIALS, getString(initials[i]))
                chat.putExtra(ChatActivity.EXTRA_AVATAR_COLOR, ContextCompat.getColor(this, colors[i]))
                startActivity(chat)
            }
        }
    }
}