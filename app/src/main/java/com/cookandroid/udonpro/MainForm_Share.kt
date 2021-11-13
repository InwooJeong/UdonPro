package com.cookandroid.udonpro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cookandroid.udonpro.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainForm_Share : Fragment() {
//    val database = Firebase.database
//    val myRef = database.reference

    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState) }
    override fun onAttach(context: Context) { super.onAttach(context) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.mainformrecyclerview, container, false)


//
//        myRef.child("book").addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//        })
        return view
    }
}
