package com.chang.jonathan.swisscheese


import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.chang.jonathan.swisscheese.main.MainScreen
import com.chang.jonathan.swisscheese.main.getMainScreenForMenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var mainPagerAdapter: MainPagerAdapter
    private lateinit var toolBar : Toolbar
    private lateinit var profileBtn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setSupportActionBar(toolbar)

        toolBar = findViewById(R.id.toolbar)
        profileBtn = toolBar.findViewById(R.id.btn_profile)
        profileBtn.setOnClickListener {
            Toast.makeText(this,"profile", Toast.LENGTH_SHORT).show()
        }
        setSupportActionBar(toolBar)
        viewPager = findViewById(R.id.view_pager)
        bottomNavigationView = findViewById(R.id.navigation_bot)
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        var pagerList = arrayListOf(MainScreen.HOME, MainScreen.PROFILE, MainScreen.SETTING)
        mainPagerAdapter.setItems(pagerList)
        scrollToScreen(MainScreen.HOME)
        selectBottomNavigationViewMenuItem(MainScreen.HOME.menuItemId)
       // supportActionBar?.title = MainScreen.HOME.titleStringId
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        viewPager.adapter = mainPagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                val selectedScreen = mainPagerAdapter.getItems()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
               // supportActionBar?.title = selectedScreen.titleStringId
            }
        })


    }

    private fun scrollToScreen(mainScreen: MainScreen) {
        val screenPosition = mainPagerAdapter.getItems().indexOf(mainScreen)
        if (screenPosition != viewPager.currentItem) {
            viewPager.currentItem = screenPosition
        }
    }

    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {
        bottomNavigationView.setOnNavigationItemSelectedListener(null)
        bottomNavigationView.selectedItemId = menuItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       getMainScreenForMenuItem(item.itemId)?.let {
           scrollToScreen(it)
          // supportActionBar?.title = it.titleStringId
           return true
       }
        return false
    }


}
