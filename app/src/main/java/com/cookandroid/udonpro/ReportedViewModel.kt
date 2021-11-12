package com.cookandroid.udonpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportedViewModel : ViewModel() {
    private val reportedRepo = ReportedRepo()
    fun fetchData(): LiveData<MutableList<ReportedListItem>>{
        val mutableData = MutableLiveData<MutableList<ReportedListItem>>()
        reportedRepo.getData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}