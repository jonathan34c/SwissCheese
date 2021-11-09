package com.chang.jonathan.swisscheese

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.chang.jonathan.swisscheese.post.ViewPostActivity

class Receiver :BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val title= intent?.extras?.getString("title")
        if (!title.isNullOrBlank()) {
            val intent = Intent(context, ViewPostActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("from","broadcast")
            context?.startActivity(intent)
        }
    }
}