package com.cookandroid.udonpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val mainFormRepo = MainFormRepo()
    fun fetchData(): LiveData<MutableList<MainFormListItem>>{
        val mutableData = MutableLiveData<MutableList<MainFormListItem>>()
        mainFormRepo.getData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}