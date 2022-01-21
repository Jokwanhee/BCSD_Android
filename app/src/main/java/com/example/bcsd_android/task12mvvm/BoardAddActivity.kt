package com.example.bcsd_android.task12mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.ActivityBoardAddBinding
import java.util.*

class BoardAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoardAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_board_add)
        editButtonEnabled()
        editBoardContents()
    }

    private fun editBoardContents() {
        binding.boardAddEditButton.setOnClickListener {
            val intent = Intent()
            val title = binding.boardAddEditTextTitle.text.toString()
            val writer = binding.boardAddEditTextWriter.text.toString()
            val createdDate = Date().time
            intent.putExtra("title", title)
            intent.putExtra("writer", writer)
            intent.putExtra("createdDate", createdDate)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun editButtonEnabled() {
        binding.boardAddEditTextTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().length > 0) {
                    binding.boardAddEditButton.isEnabled = true
                } else {
                    binding.boardAddEditButton.isEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
}
