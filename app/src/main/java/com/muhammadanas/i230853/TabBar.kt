package com.muhammadanas.i230853

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/*
  TabBar · shared top tab bar
  Wires the 5 tabs and the header Search / Chats buttons on every tab screen.
 */
object TabBar {

    fun setup(activity: AppCompatActivity, currentScreen: Class<*>) {
        link(activity, currentScreen, R.id.tab_home, HomeActivity::class.java)
        link(activity, currentScreen, R.id.tab_friends, FriendsActivity::class.java)
        link(activity, currentScreen, R.id.tab_marketplace, MarketplaceActivity::class.java)
        link(activity, currentScreen, R.id.tab_notifications, NotificationsActivity::class.java)
        link(activity, currentScreen, R.id.tab_menu, MenuActivity::class.java)

        // Header buttons: Search and Chats
        activity.findViewById<View>(R.id.iv_header_search).setOnClickListener {
            activity.startActivity(Intent(activity, SearchActivity::class.java))
        }
        activity.findViewById<View>(R.id.iv_header_chats).setOnClickListener {
            activity.startActivity(Intent(activity, ChatsActivity::class.java))
        }
    }

    // Tapping a tab brings that screen to the front (no new copy); the current tab does nothing
    private fun link(activity: AppCompatActivity, currentScreen: Class<*>, tabId: Int, target: Class<*>) {
        activity.findViewById<View>(tabId).setOnClickListener {
            if (target != currentScreen) {
                val intent = Intent(activity, target)
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                activity.startActivity(intent)
            }
        }
    }
}