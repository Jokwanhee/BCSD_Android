package com.example.bcsd_android.task12mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.ActivityLoadingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingActivity : AppCompatActivity() {
    private val DELAY_LOADING: Long = 5000

    private lateinit var binding:ActivityLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loading)

        startProgress()
        startLoading()
    }

    private fun startLoading() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            finish()
        }, DELAY_LOADING)
    }

    private fun startProgress(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            launch {
                for (i in 1..5){
                    binding.loadingCountText.text = "Loading...${i}"
                    binding.loadingProgressBar.setProgress(i)
                    delay(1000)
                }
            }
        }
    }
}