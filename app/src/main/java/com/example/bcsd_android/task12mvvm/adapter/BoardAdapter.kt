package com.example.bcsd_android.task12mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.bcsd_android.R
import com.example.bcsd_android.task12mvvm.model.BoardData
import com.example.bcsd_android.task12mvvm.model.toDateString
import kotlin.collections.ArrayList

class BoardAdapter(
    var boardList: MutableLiveData<ArrayList<BoardData>>
) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.board_title)
        val writer = itemView.findViewById<TextView>(R.id.board_writer)
        val createdDate = itemView.findViewById<TextView>(R.id.board_created_date)

        fun bind(boardData: BoardData) {
            title.text = boardData.title
            writer.text = boardData.writer
            createdDate.text = boardData.createdDate.toDateString("yyyy.MM.dd HH:mm")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_board_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(boardList.value!![position])
    }

    override fun getItemCount(): Int {
        return boardList.value!!.size
    }
}