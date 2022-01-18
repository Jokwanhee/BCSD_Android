package com.example.bcsd_android.task12mvvm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcsd_android.BaseActivity
import com.example.bcsd_android.R
import com.example.bcsd_android.Task8_11_BottomNavigation_Coroutine_Tread.FragmentTwo
import com.example.bcsd_android.databinding.ActivityMvvmMainBinding
import com.example.bcsd_android.task12mvvm.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MvvmMainActivity : AppCompatActivity() {
    private val manager = supportFragmentManager
    private var noticeBoardFragment = FragmentBoard()
    private var calculatorFragment = FragmentTwo()
    private var webFragment = FragmentWeb()

    private lateinit var binding: ActivityMvvmMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_main)
        //        binding.lifecycleOwner = this
        startLoadingActivity()
        startBottomNavigation()
    }


    override fun onStart() {
        super.onStart()
    }


    private fun startLoadingActivity() {
        val loadingIntent = Intent(this, LoadingActivity::class.java)
        startActivity(loadingIntent)
    }

    private fun startBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.main_bottom_navigation)
        manager
            .beginTransaction()
//            .replace(R.id.main_fragment_frame, noticeBoardFragment)
            .add(R.id.main_fragment_frame, webFragment)
            .add(R.id.main_fragment_frame, calculatorFragment)
            .add(R.id.main_fragment_frame, noticeBoardFragment)
            .commit()

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.notice_board -> {
                    manager
                        .beginTransaction()
//                        .replace(R.id.main_fragment_frame, noticeBoardFragment)
                        .show(noticeBoardFragment)
                        .hide(calculatorFragment)
                        .hide(webFragment)
                        .commit()
                    Toast.makeText(this, "Board", Toast.LENGTH_SHORT).show()
                }
                R.id.calculator -> {
                    manager
                        .beginTransaction()
//                        .replace(R.id.main_fragment_frame, calculatorFragment)
                        .hide(noticeBoardFragment)
                        .show(calculatorFragment)
                        .hide(webFragment)
                        .commit()
                    Toast.makeText(this, "Calculator", Toast.LENGTH_SHORT).show()
                }
                R.id.web -> {
                    manager
                        .beginTransaction()
//                        .replace(R.id.main_fragment_frame, webFragment)
                        .hide(noticeBoardFragment)
                        .hide(calculatorFragment)
                        .show(webFragment)
                        .commit()
                    Toast.makeText(this, "Web", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }
}