package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 04 · Home feed
  First main screen after logging in. Links to Comments, the Reaction picker
  and the Story viewer; the top tabs switch to the other main screens.
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

        // Composer avatar (JW) opens your Profile
        findViewById<View>(R.id.tv_composer_avatar).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // "Comment" button and "86 comments · 12 shares" -> Comments
        findViewById<View>(R.id.ll_action_comment).setOnClickListener { openComments() }
        findViewById<View>(R.id.tv_post_counts).setOnClickListener { openComments() }

        // "Like" button -> Reaction picker
        findViewById<View>(R.id.ll_action_like).setOnClickListener {
            startActivity(Intent(this, ReactionPickerActivity::class.java))
        }

        // Story cards -> Story viewer, each sending its own person and photo
        findViewById<View>(R.id.rl_story_1).setOnClickListener {
            openStory(R.string.name_omar_farooq, R.string.initials_of,
                R.color.avatar_green, R.drawable.img_story_1, true)
        }
        findViewById<View>(R.id.rl_story_2).setOnClickListener {
            openStory(R.string.name_sara_iqbal, R.string.initials_si,
                R.color.avatar_indigo, R.drawable.img_story_2, false)
        }
        findViewById<View>(R.id.rl_story_3).setOnClickListener {
            openStory(R.string.name_hamza_ali, R.string.initials_ha,
                R.color.avatar_brown, R.drawable.img_story_3, false)
        }
    }

    private fun openComments() {
        startActivity(Intent(this, CommentsActivity::class.java))
    }

    // Opens the Story viewer and passes the story's details with putExtra
    private fun openStory(name: Int, initials: Int, avatarColor: Int, photo: Int, showCaption: Boolean) {
        val intent = Intent(this, StoryViewerActivity::class.java)
        intent.putExtra(StoryViewerActivity.EXTRA_NAME, getString(name))
        intent.putExtra(StoryViewerActivity.EXTRA_INITIALS, getString(initials))
        intent.putExtra(StoryViewerActivity.EXTRA_AVATAR_COLOR, avatarColor)
        intent.putExtra(StoryViewerActivity.EXTRA_PHOTO, photo)
        intent.putExtra(StoryViewerActivity.EXTRA_SHOW_CAPTION, showCaption)
        startActivity(intent)
    }
}