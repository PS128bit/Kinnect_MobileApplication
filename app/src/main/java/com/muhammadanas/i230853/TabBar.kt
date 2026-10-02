package com.muhammadanas.i230853

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/*
  Header and top tab bar shared by the main screens (Home, Friends, Marketplace, Notifications, Menu).
  Every tab screen uses the same ids, so this one function wires them on any of them. Call it in onCreate after setContentView.
 */
object TabBar {

    fun setup(activity: AppCompatActivity, currentScreen: Class<*>) {
        // The five tabs
        link(activity, currentScreen, R.id.tab_home, HomeActivity::class.java)
        link(activity, currentScreen, R.id.tab_friends, FriendsActivity::class.java)
        link(activity, currentScreen, R.id.tab_marketplace, MarketplaceActivity::class.java)
        link(activity, currentScreen, R.id.tab_notifications, NotificationsActivity::class.java)
        link(activity, currentScreen, R.id.tab_menu, MenuActivity::class.java)

        // Header search button -> Search (a normal screen on top; Back returns here)
        activity.findViewById<View>(R.id.iv_header_search).setOnClickListener {
            activity.startActivity(Intent(activity, SearchActivity::class.java))
        }
    }

    // Makes one tab open its screen
    private fun link(activity: AppCompatActivity, currentScreen: Class<*>, tabId: Int, target: Class<*>) {
        activity.findViewById<View>(tabId).setOnClickListener {
            // Tapping the tab you are already on does nothing
            if (target != currentScreen) {
                val intent = Intent(activity, target)
                // If that tab screen is already open, bring it to the front
                // instead of creating a second copy of it
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                activity.startActivity(intent)
            }
        }
    }
}