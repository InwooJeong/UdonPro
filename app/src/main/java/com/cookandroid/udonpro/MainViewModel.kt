package com.cookandroid.udonpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val mainFormRepo = MainFormRepo()
    fun fetchDataShare(): LiveData<MutableList<MainFormListItem>>{
        val mutableData = MutableLiveData<MutableList<MainFormListItem>>()
        mainFormRepo.getDataShare().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

    fun fetchDataRequest(): LiveData<MutableList<MainFormListItem>>{
        val mutableData = MutableLiveData<MutableList<MainFormListItem>>()
        mainFormRepo.getDataRequest().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}