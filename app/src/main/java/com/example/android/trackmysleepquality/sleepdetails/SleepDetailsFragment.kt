package com.example.android.trackmysleepquality.sleepdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.databinding.SleepDetailsBinding
import com.example.android.trackmysleepquality.sleepquality.SleepQualityFragmentArgs
import com.example.android.trackmysleepquality.sleepquality.SleepQualityViewModel
import com.example.android.trackmysleepquality.sleepquality.SleepQualityViewModelFactory
import com.example.android.trackmysleepquality.sleeptracker.SleepTrackerFragmentDirections

class SleepDetailsFragment: Fragment() {

    private lateinit var viewModelFactory : SleepDetailsViewModelFactory
    private lateinit var viewModel : SleepDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(this.activity).application
        viewModelFactory = SleepDetailsViewModelFactory(SleepDetailsFragmentArgs.fromBundle(arguments!!).sleepNightKey, SleepDatabase.getInstance(application)!!.sleepDatabaseDao, application )
        viewModel = ViewModelProvider(this, viewModelFactory).get(SleepDetailsViewModel::class.java)
       val binding  = DataBindingUtil.inflate<SleepDetailsBinding>(inflater, R.layout.sleep_details, container, false )
        viewModel.sleepNight.observe(viewLifecycleOwner, Observer { sleepNight -> binding.sleepNight = sleepNight })
        binding.closeBtn.setOnClickListener {
            val action = SleepDetailsFragmentDirections.actionSleepDetailsFragmentToSleepTrackerFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
        return binding.root
    }
}