package com.example.android.trackmysleepquality.sleepdetails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import java.lang.IllegalArgumentException

class SleepDetailsViewModelFactory(val sleepNightId: Long, val databaseDao: SleepDatabaseDao, val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SleepDetailsViewModel::class.java)){
            return SleepDetailsViewModel(sleepNightId, databaseDao, application ) as T
        }
        else
            throw IllegalArgumentException("Incorrect ViewModel Type Used")

    }
}