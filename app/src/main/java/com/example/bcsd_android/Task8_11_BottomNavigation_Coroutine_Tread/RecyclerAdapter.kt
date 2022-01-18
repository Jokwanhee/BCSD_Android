package com.example.bcsd_android.Task8_11_BottomNavigation_Coroutine_Tread

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bcsd_android.R

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var noteList = mutableListOf<RecyclerData>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtName: TextView = itemView.findViewById(R.id.text_name)
        private val imgPerson: ImageView = itemView.findViewById(R.id.image_person)

        fun bind(item: RecyclerData) {
            txtName.text = item.name
            Glide.with(itemView).load(item.image).into(imgPerson)
        }
    }

    fun replaceList(newList: MutableList<RecyclerData>) {
//        noteList = newList.toMutableList()
        noteList.clear()
        noteList.addAll(newList)
        notifyDataSetChanged()
    }

    fun replaceName(position: Int, changeName: String) {
//        noteList.get(position).name = changeName
        noteList[position] = RecyclerData(changeName, R.drawable.ic_baseline_person_pin_24)
        notifyDataSetChanged()
    }

    fun addName(name: String) {
        noteList.add(RecyclerData(name, R.drawable.ic_baseline_person_pin_24))
        notifyItemInserted(itemCount)
        notifyDataSetChanged()
    }

    fun removeName(position: Int) {
        noteList.removeAt(position)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onLongClick(v: View, position: Int): Boolean
    }

    private lateinit var itemClickListener: OnItemClickListener
    private lateinit var itemLongClickListener: OnItemLongClickListener

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    fun setItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        this.itemLongClickListener = onItemLongClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemLongClickListener.onLongClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}