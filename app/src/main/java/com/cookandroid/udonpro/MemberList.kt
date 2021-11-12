package com.cookandroid.udonpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
import kotlinx.android.synthetic.main.member_list.*
import kotlinx.android.synthetic.main.mlist_item.*

class MemberList : AppCompatActivity() {
    lateinit var mAdapter : MemberListAdapter
    val viewModel by lazy { ViewModelProvider(this).get(MemberViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.member_list)

        mAdapter = MemberListAdapter(this)

        val recyclerView : RecyclerView = findViewById(R.id.memberListView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        observerData()
    }

    fun observerData(){
        viewModel.fetchData().observe(this, Observer {
            mAdapter.setListData(it)
            mAdapter.notifyDataSetChanged()
        })
    }
}