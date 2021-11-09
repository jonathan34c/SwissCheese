package com.chang.jonathan.swisscheese.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chang.jonathan.swisscheese.R

class PostFragment : Fragment() {
    private lateinit var post: Post
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeView: SwipeRefreshLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeView = view.findViewById(R.id.swipeContainer)
        recyclerView = view.findViewById(R.id.rl_post)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        post =Post()
        post.Post(requireContext())
        var posts = post.getAllPosts()
        var adapter = PostAdapter(posts, requireContext())
        recyclerView.adapter = adapter

        swipeView.setOnRefreshListener {
            swipeView.isRefreshing =true
            var posts = post.getAllPosts()
            adapter = PostAdapter(posts, requireContext())
            adapter.notifyDataSetChanged()
            recyclerView.adapter = adapter
            swipeView.isRefreshing =false
        }
    }

}