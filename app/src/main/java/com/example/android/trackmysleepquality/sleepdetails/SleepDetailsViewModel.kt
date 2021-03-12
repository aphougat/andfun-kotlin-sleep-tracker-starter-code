package com.example.android.trackmysleepquality.sleepdetails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.*

class SleepDetailsViewModel (val sleepNightId: Long, val database : SleepDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _sleepNight = MediatorLiveData<SleepNight>()
    val sleepNight : LiveData<SleepNight>
        get() = _sleepNight


    /**
     * Variable that tells the fragment whether it should navigate to [SleepTrackerFragment].
     *
     * This is `private` because we don't want to expose the ability to set [MutableLiveData] to
     * the [Fragment]
     */
    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()

    /**
     * When true immediately navigate back to the [SleepTrackerFragment]
     */
    val navigateToSleepTracker: LiveData<Boolean?>
        get() = _navigateToSleepTracker

    /**
     *
     */

    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init{
        coroutineScope.launch {
            _sleepNight.value = getNightDetailsAsync(sleepNightId)
            _navigateToSleepTracker.value = null
            //_sleepNight.addSource(database.getNightLiveData(sleepNightId!!), _sleepNight::setValue)
        }
    }


    private suspend fun getNightDetailsAsync(nightId: Long?) : SleepNight?{
        return withContext(Dispatchers.IO + viewModelJob) {
            val newNight = database.getNight(nightId!!)
            newNight
        }
    }



    /**
     * Call this immediately after navigating to [SleepTrackerFragment]
     */
    fun doneNavigating() {
        _navigateToSleepTracker.value = null
    }

    fun onClose() {
        _navigateToSleepTracker.value = true
    }

}