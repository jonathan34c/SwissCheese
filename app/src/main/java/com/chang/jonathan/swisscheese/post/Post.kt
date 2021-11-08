package com.chang.jonathan.swisscheese.post

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

import android.database.sqlite.SQLiteDatabase

class Post {
    private lateinit var db : SQLiteDatabase
    private val tableName ="post"
    fun Post(ctx: Context){
        db = ctx.openOrCreateDatabase(tableName, Context.MODE_PRIVATE, null)
    }


    fun getAllPosts():List<PostInfo>{
        val cursor: Cursor = db.rawQuery("select * from " + tableName, null)
        var list = mutableSetOf<PostInfo>()
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast){
                val title = cursor.getString(1)
                val content = cursor.getString(2)
                val username = cursor.getString(3)
                val postinfo = PostInfo(title,content,username)
                list.add(postinfo)
                cursor.moveToNext()
            }

        }
        return list.toList()

    }

    fun addPost( titleString:String, content:String, username: String){
        val values = ContentValues().apply {
            put("title", titleString)
            put("content", content)
            put("user", username)
        }
        db.insert(tableName, null, values)
    }

    fun populateDefaultPost(){
        db.execSQL("drop table if exists post")
        db.execSQL("create table post ( id integer primary key autoincrement, title text, content text, user text )")
        db.execSQL("insert into post ( title, content , user) values ('Swiss Cheese', 'The best cheese in the world with but with alot of holes in it', 'cs5277 user')")
        db.execSQL("insert into post ( title, content , user) values ('Goat Cheese', 'Hate the smell but its the best cheese in the world!')")
        db.execSQL("insert into post ( title, content , user) values ('Wonderful Cheese', '<a href=\" http://foo.com/login.php?username=%22+%2F%3E%3Cscript%3Ealert%28%27XSS%21%27%29%3B%3C%2Fscript%3E\">\n" +
                "  Click here for free money!\n" +
                "</a>', 'cs5277 user')")
    }



}