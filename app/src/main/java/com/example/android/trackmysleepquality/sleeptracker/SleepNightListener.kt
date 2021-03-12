package com.example.android.trackmysleepquality.sleeptracker

import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightListener(val clickListener : (nightId : Long) -> Unit ) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}