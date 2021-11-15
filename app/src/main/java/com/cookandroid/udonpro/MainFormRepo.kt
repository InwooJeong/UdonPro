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
    var user = FirebaseAuth.getInstance().getCurrentUser()
    var uid = if(user!= null) {user!!.getUid()} else {null}

    // 공유도서 목록
    fun getDataShare(): LiveData<MutableList<MainFormListItem>>{
        val mutableData = MutableLiveData<MutableList<MainFormListItem>>()
        val database = Firebase.database
        val myRef = database.getReference("book").orderByChild("bookType").equalTo("공유도서")

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

    // 요청도서 목록
    fun getDataRequest(): LiveData<MutableList<MainFormListItem>>{
        val mutableData = MutableLiveData<MutableList<MainFormListItem>>()
        val database = Firebase.database
        val myRef = database.getReference("book").orderByChild("bookType").equalTo("요청도서")

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

    // 찜한 도서 목록
    fun getDataFavorite(): LiveData<MutableList<MainFormListItem>>{
        val mutableData = MutableLiveData<MutableList<MainFormListItem>>()
        val database = Firebase.database
        val myRef = database.getReference(uid.toString()).child("favoritecnt").child("book")


            myRef.addValueEventListener(object : ValueEventListener {
                val listData: MutableList<MainFormListItem> = mutableListOf<MainFormListItem>()
                override fun onDataChange(snapshot: DataSnapshot) {
                    listData.clear()

                    if (snapshot.exists()) {
                        for (reportedSnapshot in snapshot.children) {
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