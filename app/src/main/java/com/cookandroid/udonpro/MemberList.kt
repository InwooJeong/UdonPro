package com.cookandroid.udonpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.member_list.*
import kotlinx.android.synthetic.main.mlist_item.*

class MemberList : AppCompatActivity() {
    lateinit var mAdapter : MemberListAdapter
    val datas = mutableListOf<MemberListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.member_list)

        initRecycler()
    }

    private fun initRecycler(){
        mAdapter = MemberListAdapter(this)
        memberListView.adapter = mAdapter

        datas.apply{
            add(MemberListItem(profile = R.drawable.ic_user_24dp, username = "user1"))
            add(MemberListItem(profile = R.drawable.ic_user_24dp, username = "user2"))
            add(MemberListItem(profile = R.drawable.ic_user_24dp, username = "user3"))
            add(MemberListItem(profile = R.drawable.ic_user_24dp, username = "user4"))

            mAdapter.datas = datas
            mAdapter.notifyDataSetChanged()
        }
    }
}