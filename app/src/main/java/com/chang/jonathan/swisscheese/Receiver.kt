package com.chang.jonathan.swisscheese

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.chang.jonathan.swisscheese.post.ViewPostActivity

class Receiver :BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val content= intent?.extras?.getString("content")
        if (!content.isNullOrBlank()) {
            val intent = Intent(context, ViewPostActivity::class.java)
            intent.putExtra("content", content)
            intent.putExtra("from","broadcast")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context?.startActivity(intent)
        }
    }
}