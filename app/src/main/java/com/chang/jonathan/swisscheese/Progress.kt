package com.chang.jonathan.swisscheese

import android.content.Context
import android.content.SharedPreferences

class Progress {
    private lateinit var prefs : SharedPreferences
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

    fun hasBroadcasr():Boolean{
        return prefs.getBoolean("broadcast", false)
    }
    fun hasDeepLink():Boolean{
        return prefs.getBoolean("deeplink", false)
    }
    fun hasLogging():Boolean{
        return prefs.getBoolean("logging", false)
    }
    fun hasHardCode():Boolean{
        return prefs.getBoolean("hardcode", false)
    }
    fun hasSql():Boolean{
        return prefs.getBoolean("sql", false)
    }
    fun hasXss():Boolean{
        return prefs.getBoolean("xss", false)
    }
    fun hasShared():Boolean{
        return prefs.getBoolean("shared", false)
    }

    fun isAllpass():Boolean{
        return hasBroadcasr() && hasDeepLink() && hasLogging() && hasHardCode() && hasSql() && hasXss() && hasShared()
    }

}