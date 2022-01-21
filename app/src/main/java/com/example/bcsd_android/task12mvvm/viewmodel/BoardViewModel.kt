package com.example.bcsd_android.task12mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bcsd_android.task12mvvm.model.BoardData

class BoardViewModel:ViewModel() {
    val itemList:MutableLiveData<ArrayList<BoardData>> by lazy {
        MutableLiveData()
    }
    var item = ArrayList<BoardData>()

    fun insertList(title: String, writer: String, createdDate: Long) {
        item.add(BoardData(title, writer, createdDate))
        itemList.postValue(item)
    }
}