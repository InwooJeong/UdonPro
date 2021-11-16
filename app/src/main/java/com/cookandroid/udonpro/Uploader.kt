package com.cookandroid.udonpro
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.uploader.*

class Uploader : AppCompatActivity() {

    lateinit var tv_userEmail2 : TextView

    lateinit var mAuth: FirebaseAuth
    lateinit var userEmail:String
    lateinit var userUid : String

    private lateinit var mAdapter: UploaderListAdapter

    val viewModel by lazy { ViewModelProvider(this).get(ViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.uploader)



        val recyclerView : RecyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = UploaderListAdapter(this)
        recyclerView.adapter = mAdapter

        observerData()

//        val recyclerView : RecyclerView = view.findViewById(R.id.viewPager)
//        recyclerView.layoutManager = GridLayoutManager(context, 2)
//        recyclerView.adapter = mAdapter


        var tv_userEmail2 : TextView =findViewById(R.id.tv_userEmail2)
        var recyclerView1  = findViewById<RecyclerView>(R.id.recyclerView1)


        val uid = intent.getStringExtra("uid").toString()
        val title = intent.getStringExtra("title").toString()
        val img = intent.getStringExtra("img").toString()
        //val userEmail = intent.getStringExtra("userEmail").toString()
        //데이터 값 받아오기.
        tv_userEmail2.text = uid

        Firebase.storage.reference.child("book_img/" + img).downloadUrl.addOnCompleteListener {
            recyclerView1.layoutManager = GridLayoutManager(this,2)
            recyclerView1.adapter = mAdapter
        }

    }


    private fun observerData(){
        viewModel.fetchDataShare().observe(this, Observer {
        mAdapter.setListData(it)
        mAdapter.notifyDataSetChanged()
        })
    }





}

