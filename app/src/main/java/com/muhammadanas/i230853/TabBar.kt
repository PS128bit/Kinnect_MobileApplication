package com.muhammadanas.i230853

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/*
  Top tab bar shared by the main screens (Home, Friends, Marketplace, Notifications, Menu).
  Every tab screen uses the same tab ids, so this one function wires the tabs on any of them.
  Calls it in onCreate after setContentView, passing the screen's own activity class.
 */
object TabBar {

    fun setup(activity: AppCompatActivity, currentScreen: Class<*>) {
        link(activity, currentScreen, R.id.tab_home, HomeActivity::class.java)
        link(activity, currentScreen, R.id.tab_friends, FriendsActivity::class.java)
        link(activity, currentScreen, R.id.tab_marketplace, MarketplaceActivity::class.java)
        link(activity, currentScreen, R.id.tab_notifications, NotificationsActivity::class.java)
        link(activity, currentScreen, R.id.tab_menu, MenuActivity::class.java)
        // Marketplace, Notifications and Menu are added here as those screens are built
    }

    // Makes one tab open its screen
    private fun link(activity: AppCompatActivity, currentScreen: Class<*>, tabId: Int, target: Class<*>) {
        activity.findViewById<View>(tabId).setOnClickListener {
            // Tapping the tab you are already on does nothing
            if (target != currentScreen) {
                val intent = Intent(activity, target)
                // If that tab screen is already open, bring it to the front instead of creating a second copy of it
                intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                activity.startActivity(intent)
            }
        }
    }
}