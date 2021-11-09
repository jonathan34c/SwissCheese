package com.chang.jonathan.swisscheese.post

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.chang.jonathan.swisscheese.R

class ViewPostActivity: AppCompatActivity() {
    private lateinit var toolBar : Toolbar
    private lateinit var webview: WebView
    private lateinit var titleTv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_post)
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        webview = findViewById(R.id.webview)
        webview.webViewClient = WebViewClient()
        var settings = webview.settings
        settings.setJavaScriptEnabled(true)
        settings.setAllowFileAccess(true)
        settings.setLoadWithOverviewMode(true)
        settings.setSupportZoom(true)
        webview.webChromeClient = WebChromeClient()

        titleTv = findViewById(R.id.tv_view_title)

        // view from post fragment
        var intent = intent
        var title = intent.getStringExtra("title")
        if(title !=null){
            titleTv.text = intent.getStringExtra("title")
            webview.loadData(intent.getStringExtra("content")?:"", "text/html; charset=utf-8","UTF-8")
        }else{

            //./adb shell am start -d "http://www.swisscheese.com/swiss?t=hi\&c=YourHacked"
            var uri = intent.data
            var content = uri?.getQueryParameter("c")
            var title = uri?.getQueryParameter("t")
            titleTv.text = title
            webview.loadData(content?:"", "text/html; charset=utf-8","UTF-8")
        }



    }

//    <a href="http://foo.com/login.php?username=%22+%2F%3E%3Cscript%3Ealert%28%27XSS%21%27%29%3B%3C%2Fscript%3E">Click here for free money!</a>

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}