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
        db.execSQL("insert into post ( title, content , user) values ('Goat Cheese', '\"<img src=\\\"https://www.seriouseats.com/thmb/B7FxU6kLnx5co7slCY7llQ1cPwQ=/1500x1125/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__images__2015__03__20150324-goat-cheese-chevre-dargental-vicky-wasik-5-2d023e6c28c34dc1b3620a81f634fd93.jpg\\\"\\n\"', 'goatcheese 2022')")
//        db.execSQL("insert into post ( username, password ) values ('angela', 'password333')")
//        db.execSQL("insert into post ( username, password ) values ('gideon', 'password999')")
//        db.execSQL("insert into post ( username, password ) values ('tyrell', 'password654')")
//        db.execSQL("insert into post ( username, password ) values ('darlene', 'password789')")
    }



}