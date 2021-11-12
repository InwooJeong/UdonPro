package com.cookandroid.udonpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.reported_list.*
import kotlinx.android.synthetic.main.rlist_item.*

class MainActivity : AppCompatActivity(){
    lateinit var rAdapter: ReportedListAdapter
    val viewModel by lazy { ViewModelProvider(this).get(ReportedViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reported_list)

        rAdapter = ReportedListAdapter(this)

        val recyclerView: RecyclerView = findViewById(R.id.reportedListView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = rAdapter
        observerData()

        var swipe = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        swipe.setOnRefreshListener {
            rAdapter = ReportedListAdapter(this)

            val recyclerView: RecyclerView = findViewById(R.id.reportedListView)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = rAdapter
            observerData()

            swipe.isRefreshing = false
        }
    }

    fun observerData(){
        viewModel.fetchData().observe(this, Observer {
            rAdapter.setListData(it)
            rAdapter.notifyDataSetChanged()
        })
    }
}
