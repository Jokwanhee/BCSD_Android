package com.example.bcsd_android.task12mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.ActivityBoardAddBinding
import com.example.bcsd_android.databinding.Task12FragmentBoardBinding
import com.example.bcsd_android.task12mvvm.model.BoardData
import com.example.bcsd_android.task12mvvm.viewmodel.MainViewModel
import java.util.*

class BoardAddActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityBoardAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board_add)
        //        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()

        editButtonEnabled()
        editBoardContents()
    }


    private fun editBoardContents() {
        binding.boardAddEditButton.setOnClickListener {
//            setResult(resultCode,intent)
            val intent = Intent()
            intent.putExtra("title", binding.boardAddEditTextTitle.text.toString())
            intent.putExtra("writer", binding.boardAddEditTextWriter.text.toString())
            intent.putExtra("time", Date().time)
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
