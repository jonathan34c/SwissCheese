package com.chang.jonathan.swisscheese.session

import android.content.Context
import android.content.SharedPreferences

class Session {
    private lateinit var prefs : SharedPreferences
    private final val userNameString ="username"
    fun Session(ctx: Context){
        prefs = ctx.getSharedPreferences("session",Context.MODE_PRIVATE)

    }
     fun setUserName(username:String){
        prefs.edit().putString(userNameString, username).commit()
    }
     fun getUserName(): String {
        return prefs.getString(userNameString, "") ?:""
    }

    fun isLogin(): Boolean{
        return prefs.getString(userNameString, "") != ""
    }
    fun logOut(){
        prefs.edit().putString(userNameString, "")
    }

}