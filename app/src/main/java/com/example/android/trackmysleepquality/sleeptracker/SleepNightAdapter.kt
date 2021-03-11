package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertLongToDateString
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter: ListAdapter<SleepNight, TextItemViewHolder>(SleepNightDiffCallback()) {
    /*var data = listOf<SleepNight>()
    set(value) {
        field =value
        notifyDataSetChanged()
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        return TextItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = getItem(position)
        val res = holder.itemView.context.resources
        holder.bind(item, res)
    }

/*
    override fun getItemCount(): Int {
        return data.size
    }
*/


}