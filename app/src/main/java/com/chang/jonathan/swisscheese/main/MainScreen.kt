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
    HOME(R.id.homeFragment, R.drawable.ic_home, "Home", PostFragment()),
    PROGRESS(R.id.progressFragment, R.drawable.ic_check, "Progress", ProgressFragment())
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}