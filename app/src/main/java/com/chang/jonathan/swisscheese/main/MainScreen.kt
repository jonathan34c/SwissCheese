package com.chang.jonathan.swisscheese.main

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.chang.jonathan.swisscheese.R
import com.chang.jonathan.swisscheese.post.PostFragment

enum class MainScreen(@IdRes val menuItemId: Int,
                      @DrawableRes val menuItemIconId: Int,
                      val titleStringId: String,
                      val fragment: Fragment) {
    HOME(R.id.homeFragment, R.drawable.ic_launcher_background, "Home", PostFragment()),
    SETTING(R.id.settingsFragment, R.drawable.ic_launcher_background, "Setting", HomeFragment()),
    PROFILE(R.id.profileFragment, R.drawable.ic_launcher_background, "Profile", HomeFragment())
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}