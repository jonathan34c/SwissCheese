package com.chang.jonathan.swisscheese.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chang.jonathan.swisscheese.Progress
import com.chang.jonathan.swisscheese.R

class ProgressFragment : Fragment() {
    private lateinit var swipeView: SwipeRefreshLayout
    private lateinit var broadCastRB: RadioButton
    private lateinit var deeplinkRB: RadioButton
    private lateinit var loggingRB: RadioButton
    private lateinit var hardCodeRB: RadioButton
    private lateinit var sqlRB: RadioButton
    private lateinit var xssRB: RadioButton
    private lateinit var sharedRB: RadioButton
    private lateinit var congrad: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeView = view.findViewById(R.id.swipeContainer)
        broadCastRB = view.findViewById(R.id.rb_broadcast)
        deeplinkRB = view.findViewById(R.id.rb_deep)
        loggingRB = view.findViewById(R.id.rb_log)
        hardCodeRB = view.findViewById(R.id.rb_credential)
        sqlRB = view.findViewById(R.id.rb_sql)
        xssRB = view.findViewById(R.id.rb_xss)
        sharedRB = view.findViewById(R.id.rb_share)
        congrad = view.findViewById(R.id.tv_congrad)
        checkProgress()
        swipeView.setOnRefreshListener {
            swipeView.isRefreshing =true
            checkProgress()
        }
    }

    private fun checkProgress(){
        var progess = Progress()
        progess.Progress(requireContext())
        broadCastRB.isChecked = progess.hasBroadcasr()
        deeplinkRB.isChecked = progess.hasDeepLink()
        loggingRB.isChecked = progess.hasLogging()
        hardCodeRB.isChecked = progess.hasHardCode()
        sqlRB.isChecked = progess.hasSql()
        xssRB.isChecked = progess.hasXss()
        sharedRB.isChecked = progess.hasShared()
        congrad.visibility = if(progess.isAllpass()){
            View.VISIBLE
        }else{
            View.GONE
        }
        swipeView.isRefreshing =false
    }

}