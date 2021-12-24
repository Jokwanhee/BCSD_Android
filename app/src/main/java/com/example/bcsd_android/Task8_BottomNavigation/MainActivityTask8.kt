package com.example.bcsd_android.Task8_BottomNavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.bcsd_android.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityTask8 : AppCompatActivity() {
    private val fragmentManager:FragmentManager = supportFragmentManager
    private val noteFragment = FragmentOne()
    private val calculatorFragment = FragmentTwo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task8)

        startBottomNavigation()
    }

    private fun startBottomNavigation(){
        val bottomNavi = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        fragmentManager
            .beginTransaction()
            .add(R.id.frame, calculatorFragment)
            .add(R.id.frame, noteFragment)
            .commit()

        bottomNavi.setOnItemSelectedListener {
            when (it.itemId){
                R.id.menu_note_id -> {
                    fragmentManager
                        .beginTransaction()
                        .show(noteFragment)
                        .commit()
                    fragmentManager
                        .beginTransaction()
                        .hide(calculatorFragment)
                        .commit()
                    Toast.makeText(this, "Note", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_calc_id ->{
                    fragmentManager
                        .beginTransaction()
                        .show(calculatorFragment)
                        .commit()
                    fragmentManager
                        .beginTransaction()
                        .hide(noteFragment)
                        .commit()
                    Toast.makeText(this, "Calculator", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

    }
}