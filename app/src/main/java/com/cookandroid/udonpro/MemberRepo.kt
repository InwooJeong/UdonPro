package com.cookandroid.udonpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MemberRepo {
    fun getData(): LiveData<MutableList<MemberListItem>>{
        val mutableData = MutableLiveData<MutableList<MemberListItem>>()
        val database = Firebase.database
        val myRef = database.getReference("UdonProject").child("UserAccount").orderByChild("reported").startAt(3.0)
        myRef.addValueEventListener(object : ValueEventListener{
            val listData : MutableList<MemberListItem> = mutableListOf<MemberListItem>()
            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear()

                if(snapshot.exists()){
                    for(memberSnapshot in snapshot.children){
                        val getData = memberSnapshot.getValue(MemberListItem::class.java)
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