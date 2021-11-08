package com.chang.jonathan.swisscheese.post

import java.io.Serializable

class PostInfo(val title:String, val content: String, val userName: String) :Serializable {
    fun getPostUsername():String{
        return userName
    }
    fun getPostTitle():String{
        return title
    }
    fun getPostContent():String{
        return content
    }
}