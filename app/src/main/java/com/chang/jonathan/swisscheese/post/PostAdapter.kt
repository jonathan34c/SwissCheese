package com.chang.jonathan.swisscheese.post

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.chang.jonathan.swisscheese.R
import com.chang.jonathan.swisscheese.login.LoginActivity

class PostAdapter(private val list: List<PostInfo>, private val contect: Context):
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val titleTv: TextView = itemView.findViewById(R.id.tv_post_title)
        val contentTv: TextView = itemView.findViewById(R.id.tv_post_content)
        val userTv: TextView = itemView.findViewById(R.id.tv_post_user)
        val cardCv: CardView = itemView.findViewById(R.id.cv_post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = list[position]
        holder.titleTv.text = post.title
        holder.contentTv.text = post.content
        holder.userTv.text = post.userName
        holder.cardCv.setOnClickListener {
            val intent = Intent(contect, ViewPostActivity::class.java)
            intent.putExtra("title", post.title)
            intent.putExtra("content", post.content)
            contect.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}