package com.chang.jonathan.swisscheese.post

class PostInfo(val title:String, val content: String, val userName: String) {
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