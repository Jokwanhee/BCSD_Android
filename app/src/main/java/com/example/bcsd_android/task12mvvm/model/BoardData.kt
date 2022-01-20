package com.example.bcsd_android.task12mvvm.model

import java.text.SimpleDateFormat
import java.util.*

data class BoardData(
    val title:String,
    val writer:String,
    val time:Long
)

fun Long.toDateString(format:String): String{
    val simpleDataFormat = SimpleDateFormat(format)
    return simpleDataFormat.format((Date(this)))
}