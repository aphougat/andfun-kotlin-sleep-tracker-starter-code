package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertLongToDateString
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<SleepNight>()
    set(value) {
        field =value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        return TextItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_sleep_night, parent, false))
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.qualityImage.setImageResource(when (item.sleepQuality){
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_launcher_sleep_tracker_foreground
        }

        )
        holder.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
        holder.sleepQuality.text = convertLongToDateString(item.nightId)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}