package com.example.bcsd_android.task12mvvm

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcsd_android.BaseFragment
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.Task12FragmentBoardBinding
import com.example.bcsd_android.task12mvvm.model.BoardData
import com.example.bcsd_android.task12mvvm.viewmodel.MainViewModel

class FragmentBoard : Fragment() {
    private val adapter = BoardAdapter()

    private lateinit var binding: Task12FragmentBoardBinding

    private val startResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == AppCompatActivity.RESULT_OK){
            val title = it.data!!.getStringExtra("title")
            val writer = it.data!!.getStringExtra("writer")
            val createdDate = it.data!!.getLongExtra("time",0)
            adapter.addList(title.toString(),writer.toString(),createdDate)
            binding.mainRecyclerView.adapter = adapter
            binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.task12_fragment_board, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val test = model.livedata.observe(viewLifecycleOwner, Observer {
//            Log.d("testing","${it}")
//        })

        startAddButton()
    }


    private fun startAddButton() {
        binding.mainAddButton.setOnClickListener {
            activity?.let {
                val intent = Intent(context, BoardAddActivity::class.java)
                startResult.launch(intent)
            }
        }
    }
}