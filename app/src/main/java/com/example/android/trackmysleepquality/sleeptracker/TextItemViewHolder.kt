package com.example.android.trackmysleepquality.sleeptracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertLongToDateString
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

class TextItemViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root){

    //val layout = R.layout.list_item_sleep_night
    //val textView: TextView = view.findViewById(R.id.recyclerTextView)


    companion object {
        fun from(parent: ViewGroup) : TextItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
            return TextItemViewHolder(binding)
        }
    }

    fun bind(
        item: SleepNight,
        clickListener: SleepNightListener
    ) {

        /*binding.qualityImage.setImageResource(
            when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_launcher_sleep_tracker_foreground
            }

        )
        binding.qualityString.text = convertNumericQualityToString(item.sleepQuality, res)
        binding.sleepLength.text = convertLongToDateString(item.nightId)*/


        binding.sleepNight = item
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

}
