package com.chang.jonathan.swisscheese


import android.content.Intent
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
import com.chang.jonathan.swisscheese.login.LoginActivity
import com.chang.jonathan.swisscheese.main.MainScreen
import com.chang.jonathan.swisscheese.main.getMainScreenForMenuItem
import com.chang.jonathan.swisscheese.post.Post
import com.chang.jonathan.swisscheese.post.PostActivity
import com.chang.jonathan.swisscheese.session.Session
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var mainPagerAdapter: MainPagerAdapter
    private lateinit var toolBar : Toolbar
    private lateinit var profileBtn : ImageView
    private lateinit var addPostBtn: ImageView
    private lateinit var session: Session
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setSupportActionBar(toolbar)

        session = Session()
        session.Session(this)
        val post =Post()
        post.Post(this)
        post.populateDefaultPost()
        toolBar = findViewById(R.id.toolbar)
        profileBtn = toolBar.findViewById(R.id.btn_profile)
        profileBtn.setOnClickListener {
            if(!session.isLogin()){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }else{
                logout()
            }

        }
        addPostBtn = toolBar.findViewById(R.id.btn_add)
        addPostBtn.setOnClickListener {
            if(!session.isLogin()){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, PostActivity::class.java)
                startActivity(intent)
            }
        }
        setSupportActionBar(toolBar)
        viewPager = findViewById(R.id.view_pager)
        bottomNavigationView = findViewById(R.id.navigation_bot)
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager)
        val pagerList = arrayListOf(MainScreen.HOME, MainScreen.PROFILE, MainScreen.SETTING)
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

    override fun onResume() {
        if(session.isLogin()){
            profileBtn.setImageResource(R.drawable.ic_logout)
        }
        super.onResume()
    }

    fun logout(){
        session.logOut()
        profileBtn.setImageResource(R.drawable.ic_account)
        Toast.makeText(this, "You are now Logout", Toast.LENGTH_LONG).show()
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
