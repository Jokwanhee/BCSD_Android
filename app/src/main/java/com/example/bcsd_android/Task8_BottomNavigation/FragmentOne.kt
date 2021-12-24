package com.example.bcsd_android.Task8_BottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.Activity8FragmentOneBinding

class FragmentOne:Fragment() {
    private var mBinding:Activity8FragmentOneBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = Activity8FragmentOneBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteList = ArrayList<RecyclerData>()
        val adapter = RecyclerAdapter(noteList)
        binding.noteRecycler.addItemDecoration(VerticalDecorator(10))
        binding.noteRecycler.addItemDecoration(HorizontalDecorator(10))

        binding.btnExtra.setOnClickListener {
            noteList.apply {
                var result = binding.inputName.text.toString()
                add(RecyclerData(name = result,image = R.drawable.ic_baseline_person_pin_24))
                binding.noteRecycler.adapter = adapter
                binding.noteRecycler.layoutManager = LinearLayoutManager(view.context)
            }
        }
        adapter.notifyDataSetChanged()
    }


    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
}