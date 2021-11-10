package com.chang.jonathan.swisscheese.post

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.chang.jonathan.swisscheese.R
import com.chang.jonathan.swisscheese.session.Session

class PostActivity :AppCompatActivity(){
    private val TAG = "PostActivity"
    private lateinit var toolBar : Toolbar
    private lateinit var titleField: EditText
    private lateinit var contentField: EditText
    private lateinit var submitBtn : Button
    private lateinit var session: Session
    private lateinit var post: Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        session = Session()
        session.Session(this)
        post =Post()
        post.Post(this)
        titleField = findViewById(R.id.et_title)
        contentField = findViewById(R.id.et_content)
        submitBtn = findViewById(R.id.btn_submit)
        submitBtn.setOnClickListener {
            val title = titleField.text.toString().trim()
            val content = contentField.text.toString().trim()
            if(title.isNullOrEmpty()){
                Toast.makeText(this, "Title can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(content.isNullOrEmpty()){
                Toast.makeText(this, "Content can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            post.addPost(title,content,session.getUserName())
            finish()

        }

    }

    override fun onResume() {
        super.onResume()

        var posrs = post.getAllPosts()
        for(p in posrs){
            Log.d(TAG,p.title)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}