package com.cookandroid.udonpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.reported_list.*
import kotlinx.android.synthetic.main.rlist_item.*

class ReportedList : AppCompatActivity(){
    lateinit var rAdapter: ReportedListAdapter
    val datas = mutableListOf<ReportedListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reported_list)

        initRecycler()
    }

    private fun initRecycler(){
        rAdapter = ReportedListAdapter(this)
        reportedListView.adapter = rAdapter

        datas.apply{
            add(ReportedListItem(bookname = "꿈나무1", bookpic = R.drawable.ic_favorite_24dp))
            add(ReportedListItem(bookname = "꿈나무2", bookpic = R.drawable.ic_favorite_24dp))
            add(ReportedListItem(bookname = "꿈나무3", bookpic = R.drawable.ic_favorite_24dp))
            add(ReportedListItem(bookname = "꿈나무4", bookpic = R.drawable.ic_favorite_24dp))

            rAdapter.datas = datas
            rAdapter.notifyDataSetChanged()
        }

    }
}