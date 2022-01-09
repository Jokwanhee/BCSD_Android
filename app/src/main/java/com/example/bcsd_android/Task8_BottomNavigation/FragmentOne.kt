package com.example.bcsd_android.Task8_BottomNavigation

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.Activity8FragmentOneBinding

class FragmentOne : Fragment() {
    private var mBinding: Activity8FragmentOneBinding? = null
    private val binding get() = mBinding!!
    val noteList = mutableListOf<RecyclerData>()
    val adapter = RecyclerAdapter()

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

        binding.noteRecycler.addItemDecoration(VerticalDecorator(10))
        binding.noteRecycler.addItemDecoration(HorizontalDecorator(10))

        editEnabled(binding.inputName, binding.btnExtra) // 버튼 활성화 및 비활성화 (글자 입력 x -> 비활성화)
        addButton() // 버튼 클릭 시 recyclerView 항목 하나하나 추가
        clickView() // recyclerView 항목 클릭 시 AlertDialog 표시 (삭제 및 취소)
    }

    private fun clickView() {
        // EventListener 리스트 누를 때(기본)
        adapter.setItemClickListener(object : RecyclerAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val builder = AlertDialog.Builder(view?.context)
                builder.setTitle(R.string.title)
                builder.setMessage(R.string.message_contents)
                builder.setPositiveButton(R.string.positive) { dialog, which ->
                    adapter.removeName(position)
                }
                builder.setNegativeButton(R.string.negative) { dialog, which ->
                    Toast.makeText(view?.context, "취소", Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }
        })
        // EventListener 리스트 길게 누를 때
        adapter.setItemLongClickListener(object : RecyclerAdapter.OnItemLongClickListener {
            override fun onLongClick(v: View, position: Int): Boolean {
                val dialogView = layoutInflater.inflate(R.layout.note_custom_alertdialog, null)
                val dialogName = dialogView.findViewById<EditText>(R.id.custom_edit_view)
                val btnChange = dialogView.findViewById<Button>(R.id.custom_button_view)
                val btnCancel = dialogView.findViewById<Button>(R.id.custom_cancel_button_view)
                val builder = AlertDialog.Builder(view?.context)
                    .setTitle(R.string.long_title)
                    .create()
                editEnabled(dialogName, btnChange)

                btnChange.setOnClickListener {
                    adapter.replaceName(position, dialogName.text.toString())
                    binding.noteRecycler.adapter = adapter
                    binding.noteRecycler.layoutManager = LinearLayoutManager(view?.context)
                    builder.dismiss()
                }

                btnCancel.setOnClickListener {
                    Toast.makeText(view?.context, "취소", Toast.LENGTH_SHORT).show()
                    builder.dismiss()
                }

                builder.setView(dialogView)
                builder.show()
                return true
            }
        })
    }

    private fun addButton() {
        adapter.replaceList(noteList)
        binding.btnExtra.setOnClickListener {
            adapter.addName(name = binding.inputName.text.toString())
        }
        binding.noteRecycler.adapter = adapter
        binding.noteRecycler.layoutManager = LinearLayoutManager(view?.context)
    }


    private fun editEnabled(edit: EditText, button: Button) {
        edit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().length > 0) {
                    button.isEnabled = true
                } else {
                    button.isEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }


    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

}