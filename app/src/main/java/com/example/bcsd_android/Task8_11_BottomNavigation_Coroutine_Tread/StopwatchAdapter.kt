package com.example.bcsd_android.Task8_11_BottomNavigation_Coroutine_Tread

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bcsd_android.R
import java.text.SimpleDateFormat

class StopwatchAdapter(
    val labList: MutableList<StopwatchData>
) : RecyclerView.Adapter<StopwatchAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labText = itemView.findViewById<TextView>(R.id.stopwatch_item_lab_text)
        val time = itemView.findViewById<TextView>(R.id.stopwatch_item_time)

        fun bind(item: StopwatchData, position: Int) {
            labText.text = "랩" + (position + 1)
            time.text = SimpleDateFormat("mm:ss:SS").format(item.time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_stopwatch, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(labList[position], position)
    }

    override fun getItemCount(): Int {
        return labList.size
    }
}