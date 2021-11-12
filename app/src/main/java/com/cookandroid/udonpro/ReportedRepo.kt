package com.cookandroid.udonpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ReportedRepo {
    fun getData(): LiveData<MutableList<ReportedListItem>>{
        val mutableData = MutableLiveData<MutableList<ReportedListItem>>()
        val database = Firebase.database
        val myRef = database.getReference("book")
        myRef.addValueEventListener(object : ValueEventListener{
            val listData : MutableList<ReportedListItem> = mutableListOf<ReportedListItem>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(reportedSnapshot in snapshot.children){
                        val getData = reportedSnapshot.getValue(ReportedListItem::class.java)
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