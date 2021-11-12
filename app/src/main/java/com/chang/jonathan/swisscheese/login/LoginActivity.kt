package com.chang.jonathan.swisscheese.login

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.chang.jonathan.swisscheese.Progress
import com.chang.jonathan.swisscheese.R
import com.chang.jonathan.swisscheese.session.Session

class LoginActivity: AppCompatActivity() {
    private lateinit var toolBar : Toolbar
    private lateinit var usernameField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginBtn : Button
    private val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        usernameField = findViewById(R.id.et_username)
        passwordField = findViewById(R.id.et_password)
        loginBtn = findViewById(R.id.btn_login)

        val db = populateDatabase()

        loginBtn.setOnClickListener {

            if(usernameField.text.toString() == "secretUsername" && passwordField.text.toString() == "secretPassword"){
                val session = Session()
                session.Session(this)
                session.setUserName("secretUsername")
                val progess = Progress()
                progess.Progress(this)
                progess.setProgress("logging")
                progess.setProgress("hardcode")
                Toast.makeText(this, "login success", Toast.LENGTH_LONG).show()
                finish()
                return@setOnClickListener
            }else{
                Log.d(TAG, "its not secretUsername and secretPassword")
            }
            if(usernameField.text.toString() == "sharedUsername" && passwordField.text.toString() == "sharedPassword"){
                val progess = Progress()
                progess.Progress(this)
                progess.setProgress("shared")
                val session = Session()
                session.Session(this)
                session.setUserName("sharedUsername")
                Toast.makeText(this, "login success", Toast.LENGTH_LONG).show()
                finish()
                return@setOnClickListener
            }
            // admin' or 1=1--
            val cursor: Cursor = db.rawQuery("select * from user where username = '" + usernameField.text.toString() + "' and password = '" + passwordField.text.toString() + "'", null)
            val data = StringBuilder()
            val username = StringBuilder()
            if (cursor.count !=0) {
                cursor.moveToFirst()
                do {
                    val user = cursor.getString(1)
                    val pass = cursor.getString(2)
                    data.append("User: $user \nPass: $pass\n")
                    username.append(user)
                } while (cursor.moveToNext())
            }
            cursor.close()

            Log.d(TAG, data.toString())
            if(data.isNotEmpty()){
                Toast.makeText(this, "login success", Toast.LENGTH_LONG).show()
                val session = Session()
                session.Session(this)
                session.setUserName(username.toString())
                val progess = Progress()
                progess.Progress(this)
                progess.setProgress("sql")
                finish()
            }else{
                Toast.makeText(this, "login failed", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun populateDatabase(): SQLiteDatabase {
        val db = openOrCreateDatabase("userData", android.content.Context.MODE_PRIVATE, null)
        db.execSQL("drop table if exists user")
        db.execSQL("create table user ( id integer primary key autoincrement, username text, password text )")
        db.execSQL("insert into user ( username, password ) values ('admin', 'password123')")
        db.execSQL("insert into user ( username, password ) values ('elliot', 'password555')")
        db.execSQL("insert into user ( username, password ) values ('angela', 'password333')")
        db.execSQL("insert into user ( username, password ) values ('gideon', 'password999')")
        db.execSQL("insert into user ( username, password ) values ('tyrell', 'password654')")
        db.execSQL("insert into user ( username, password ) values ('darlene', 'password789')")
        return db
    }

}