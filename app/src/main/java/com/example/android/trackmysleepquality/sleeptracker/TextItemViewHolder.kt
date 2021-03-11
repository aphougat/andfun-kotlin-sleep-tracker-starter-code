package com.example.android.trackmysleepquality.sleeptracker

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R

class TextItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val sleepQuality : TextView = view.findViewById(R.id.sleep_length)
    val qualityString : TextView = view.findViewById(R.id.quality_string)
    val qualityImage :ImageView = view.findViewById(R.id.quality_image)

    //val textView: TextView = view.findViewById(R.id.recyclerTextView)


}
