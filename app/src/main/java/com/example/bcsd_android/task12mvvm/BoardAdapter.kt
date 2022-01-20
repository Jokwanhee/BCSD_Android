package com.example.bcsd_android.task12mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bcsd_android.R
import com.example.bcsd_android.task12mvvm.model.BoardData
import com.example.bcsd_android.task12mvvm.model.toDateString
import java.util.*
import kotlin.collections.ArrayList

class BoardAdapter(
) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    val boardList = ArrayList<BoardData>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.board_title)
        val writer = itemView.findViewById<TextView>(R.id.board_writer)
        val createdDate = itemView.findViewById<TextView>(R.id.board_created_date)

        fun bind(boardData: BoardData) {
            title.text = boardData.title
            writer.text = boardData.writer
            createdDate.text = boardData.time.toDateString("yyyy.MM.dd HH:mm")
        }
    }

    fun addList(title: String, writer: String, time:Long) {
        boardList.add(BoardData(title, writer, time))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_board_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(boardList[position])
    }

    override fun getItemCount(): Int {
        return boardList.size
    }
}