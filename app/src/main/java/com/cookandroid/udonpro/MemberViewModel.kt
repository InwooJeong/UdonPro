package com.cookandroid.udonpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MemberViewModel : ViewModel() {
    private val memberRepo = MemberRepo()
    fun fetchData(): LiveData<MutableList<MemberListItem>>{
        val mutableData = MutableLiveData<MutableList<MemberListItem>>()
        memberRepo.getData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}