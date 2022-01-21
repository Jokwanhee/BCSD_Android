package com.example.bcsd_android.task12mvvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.Task12FragmentBoardBinding
import com.example.bcsd_android.task12mvvm.adapter.BoardAdapter
import com.example.bcsd_android.task12mvvm.model.BoardData
import com.example.bcsd_android.task12mvvm.viewmodel.BoardViewModel

class FragmentBoard : Fragment() {
    private lateinit var adapter: BoardAdapter

    private lateinit var binding: Task12FragmentBoardBinding
    private lateinit var viewModel: BoardViewModel
    var data = MutableLiveData<ArrayList<BoardData>>()

    private val startResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val title = it.data!!.getStringExtra("title")
                val writer = it.data!!.getStringExtra("writer")
                val createdDate = it.data!!.getLongExtra("createdDate", 0)
                viewModel.insertList(title.toString(), writer.toString(), createdDate)
                val listObserver = Observer<ArrayList<BoardData>> {
                    data.value = it
                    adapter = BoardAdapter(data)
                    binding.mainRecyclerView.adapter = adapter
                    binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)
                }
                viewModel.itemList.observe(viewLifecycleOwner, listObserver)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
            .get(BoardViewModel::class.java)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.task12_fragment_board, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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