package com.muhammadanas.i230853

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
  SCREEN 19 · Menu
  Profile shortcut, app shortcuts and Log out. Opened from the Menu tab.
 */
class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        // Pad the layout so content never sits under the status or navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Top tab bar
        TabBar.setup(this, MenuActivity::class.java)

        // Profile card at the top of Menu opens your Profile
        findViewById<View>(R.id.rl_menu_profile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Shortcut tiles that lead to existing tab screens
        findViewById<View>(R.id.ll_shortcut_friends).setOnClickListener {
            openTab(FriendsActivity::class.java)
        }
        findViewById<View>(R.id.ll_shortcut_marketplace).setOnClickListener {
            openTab(MarketplaceActivity::class.java)
        }

        // Log out -> Log in.
        // NEW_TASK + CLEAR_TASK remove every screen from the back stack,
        // so Back on Log in closes the app instead of returning to Home.
        findViewById<View>(R.id.ll_log_out).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    // Opens a tab screen the same way the tab bar does (no duplicate copies)
    private fun openTab(screen: Class<*>) {
        val intent = Intent(this, screen)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
        startActivity(intent)
    }
}