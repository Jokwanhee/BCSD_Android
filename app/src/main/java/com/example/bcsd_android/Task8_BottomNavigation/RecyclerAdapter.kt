package com.example.bcsd_android.Task8_BottomNavigation

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bcsd_android.R

class RecyclerAdapter(
    val itemList: ArrayList<RecyclerData>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtName:TextView = itemView.findViewById(R.id.text_name)
        private val imgPerson:ImageView = itemView.findViewById(R.id.image_person)

        fun bind(item:RecyclerData){
            txtName.text = item.name
            Glide.with(itemView).load(item.image).into(imgPerson)
        }

//        init {
//            txtName = itemView.findViewById(R.id.text_name)
//            imgPerson = itemView.findViewById(R.id.image_person)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.txtName.setText(itemList.get(position).name)
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}