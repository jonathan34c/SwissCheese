package com.chang.jonathan.swisscheese

import android.content.Context
import android.content.SharedPreferences

class Progress {
    private lateinit var prefs : SharedPreferences
    private final val userNameString ="username"
    fun Progress(ctx: Context){
        prefs = ctx.getSharedPreferences("progress", Context.MODE_PRIVATE)
    }
    fun setProgress( method:String){
        when(method){
            "broadcast" ->prefs.edit().putBoolean("broadcast", true).commit()
            "deeplink" -> prefs.edit().putBoolean("deeplink", true).commit()
            "logging" -> prefs.edit().putBoolean("logging", true).commit()
            "hardcode" -> prefs.edit().putBoolean("hardcode", true).commit()
            "sql" -> prefs.edit().putBoolean("sql", true).commit()
            "xss" -> prefs.edit().putBoolean("xss", true).commit()
            "shared" -> prefs.edit().putBoolean("shared", true).commit()
        }
    }


}