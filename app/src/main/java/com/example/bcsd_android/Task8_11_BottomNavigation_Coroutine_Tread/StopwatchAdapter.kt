package com.example.bcsd_android.Task8_11_BottomNavigation_Coroutine_Tread

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bcsd_android.R

class StopwatchAdapter(
    val labList: MutableList<StopwatchData>
) : RecyclerView.Adapter<StopwatchAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labText = itemView.findViewById<TextView>(R.id.stopwatch_item_lab_text)
        val millisecond = itemView.findViewById<TextView>(R.id.stopwatch_item_millisecond)
        val second = itemView.findViewById<TextView>(R.id.stopwatch_item_second)
        val minutes = itemView.findViewById<TextView>(R.id.stopwatch_item_minutes)

        fun bind(item: StopwatchData, position: Int) {
            labText.text = "랩" + (position + 1)
            if (item.millisec < 10) {
                millisecond.text = "0" + item.millisec
            } else {
                millisecond.text = item.millisec.toString()
            }
            if (item.sec < 10) {
                second.text = "0" + item.sec
            } else {
                second.text = item.sec.toString()
            }
            if (item.min < 10) {
                minutes.text = "0" + item.min
            } else {
                minutes.text = item.min.toString()
            }
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