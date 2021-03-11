/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.*


/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

        private var viewModelJob = Job()
        private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
        //private val coroutineScopeIO = CoroutineScope(Dispatchers.IO + viewModelJob)
        private val _sleepRecord = MutableLiveData<SleepNight>()
        val sleepRecord : LiveData<SleepNight>
                get() = _sleepRecord

        val sleepRecords : LiveData<List<SleepNight>>
                get() = database.getAllNights()
        /*
        val sleepRecordsString = Transformations.map(sleepRecords){
                nights -> formatNights(nights, application.resources)
        }
*/
        private var _startButtonVisibility = MutableLiveData<Boolean>()
        val startButtonVisibility : LiveData<Boolean>
        get() = _startButtonVisibility

        private var _stopButtonVisibility = MutableLiveData<Boolean>()
        val stopButtonVisibility : LiveData<Boolean>
                get() = _stopButtonVisibility

        private val _sleepFinished = MutableLiveData<Boolean>()
        val sleepFinished: LiveData<Boolean>
        get() = _sleepFinished

        private var _showSnackbarEvent = MutableLiveData<Boolean>()

        val showSnackBarEvent: LiveData<Boolean>
                get() = _showSnackbarEvent

         init {
             _sleepFinished.value = false
                 _startButtonVisibility.value = true
                 _stopButtonVisibility.value = false
         }

        fun doneShowingSnackbar() {
                _showSnackbarEvent.value = false
        }
        fun startSleep(){
                if (_sleepRecord.value == null) {
                        val currentTime = System.currentTimeMillis()
                        val night = SleepNight(sleepTimeInMillis = currentTime)
                        _startButtonVisibility.value = false
                        _stopButtonVisibility.value = true
                        coroutineScope.launch {
                                _sleepRecord.value = asyncStartSleep(night)
                        }
                }
        }

        private suspend fun asyncStartSleep(night: SleepNight) : SleepNight? {
                return withContext(Dispatchers.IO){
                        database.insert(night)
                        val newNight = database.getTonight()
                        newNight
                }


        }

        fun stopSleep(){
                val currentTime = System.currentTimeMillis()
                _startButtonVisibility.value = true
                _stopButtonVisibility.value = false
                coroutineScope.launch {
                        var oldNight =  _sleepRecord.value ?: return@launch
                        oldNight.wakeupTimeInMillis = currentTime
                        asyncEndSleep(oldNight)
                        _sleepFinished.value = true
                }
        }

        private suspend fun asyncEndSleep(sleepNight: SleepNight)  {
                return withContext(Dispatchers.IO){
                        database.update(sleepNight!!)
                }
        }

        fun clear()
        {
                coroutineScope.launch { clearAll() }
                _showSnackbarEvent.value = true
        }

        override fun onCleared() {
                super.onCleared()
                viewModelJob.cancel()
        }

        private suspend fun clearAll() = withContext(Dispatchers.IO){
                database.clearAll()
        }
}


