package com.cookandroid.udonpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MainFormRepo {

    fun getDataShare(): LiveData<MutableList<MainFormListItem>>{
        val mutableData = MutableLiveData<MutableList<MainFormListItem>>()
        val database = Firebase.database
        val myRef = database.getReference("book").child("공유도서")

        myRef.addValueEventListener(object : ValueEventListener{
            val listData : MutableList<MainFormListItem> = mutableListOf<MainFormListItem>()
            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear()

                if(snapshot.exists()){
                    for(reportedSnapshot in snapshot.children){
                        val getData = reportedSnapshot.getValue(MainFormListItem::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return mutableData
    }

    fun getDataRequest(): LiveData<MutableList<MainFormListItem>>{
        val mutableData = MutableLiveData<MutableList<MainFormListItem>>()
        val database = Firebase.database
        val myRef = database.getReference("book").child("요청도서")

        myRef.addValueEventListener(object : ValueEventListener{
            val listData : MutableList<MainFormListItem> = mutableListOf<MainFormListItem>()
            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear()

                if(snapshot.exists()){
                    for(reportedSnapshot in snapshot.children){
                        val getData = reportedSnapshot.getValue(MainFormListItem::class.java)
                        listData.add(getData!!)

                        mutableData.value = listData
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return mutableData
    }

}