package com.example.bcsd_android.Task8_11_BottomNavigation_Coroutine_Tread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bcsd_android.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityTask8 : AppCompatActivity() {
    private var noteFragment = FragmentOne()
    private var calculatorFragment = FragmentTwo()
    private var stopwatchFragment = FragmentThree()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task8)

        startBottomNavigation()
    }

    private fun startBottomNavigation() {
        val bottomNavi = findViewById<BottomNavigationView>(R.id.bottom_navigation)

//        replaceFragment(noteFragment)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame, stopwatchFragment)
            .add(R.id.frame, calculatorFragment)
            .add(R.id.frame, noteFragment)
            .commit()

        bottomNavi.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_note_id -> {
//                    replaceFragment(noteFragment)
                    supportFragmentManager
                        .beginTransaction()
                        .show(noteFragment)
                        .hide(calculatorFragment)
                        .hide(stopwatchFragment)
                        .commit()
                    Toast.makeText(this, "Note", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_calc_id -> {
//                    replaceFragment(calculatorFragment)
                    supportFragmentManager
                        .beginTransaction()
                        .hide(noteFragment)
                        .show(calculatorFragment)
                        .hide(stopwatchFragment)
                        .commit()
                    Toast.makeText(this, "Calculator", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_stopwatch_id -> {
//                    replaceFragment(stopwatchFragment)
                    supportFragmentManager
                        .beginTransaction()
                        .hide(noteFragment)
                        .hide(calculatorFragment)
                        .show(stopwatchFragment)
                        .commit()
                    Toast.makeText(this, "Stopwatch", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}