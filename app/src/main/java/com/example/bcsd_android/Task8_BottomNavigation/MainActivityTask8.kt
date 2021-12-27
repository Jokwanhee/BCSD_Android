package com.example.bcsd_android.Task8_BottomNavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.bcsd_android.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityTask8 : AppCompatActivity() {
    private val noteFragment = FragmentOne()
    private val calculatorFragment = FragmentTwo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_task8)

        startBottomNavigation()
    }

    private fun startBottomNavigation() {
        val bottomNavi = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        note(noteFragment)

        bottomNavi.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_note_id -> {
                    note(noteFragment)
                    Toast.makeText(this, "Note", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_calc_id -> {
                    calculator(calculatorFragment)
                    Toast.makeText(this, "Calculator", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    private fun note(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    private fun calculator(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

}