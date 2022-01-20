package com.example.bcsd_android.task12mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bcsd_android.task12mvvm.model.BoardData

class MainViewModel : ViewModel(){
    var title:MutableLiveData<String> = MutableLiveData()
    var writer:MutableLiveData<String> = MutableLiveData()
}