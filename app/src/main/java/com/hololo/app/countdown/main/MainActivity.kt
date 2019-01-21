package com.hololo.app.countdown.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProviders
import com.hololo.app.countdown.R
import com.hololo.app.countdown.core.Constants
import com.hololo.app.countdown.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        @Override
        override fun onReceive(context: Context, intent: Intent) {
            val remainingTime = intent.getLongExtra(Constants.IntentNames.REMAINING_TIME, 0L)
            val countdownFinish = intent.getBooleanExtra(Constants.IntentNames.COUNTDOWN_FINISH, false)
            viewModel.timeInMilliseconds.setRemainingTime(remainingTime)
            if (countdownFinish) {
                viewModel.finishTimer.set(true)
            }
        }
    };

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        binding.viewModel = viewModel
        binding.viewModel?.updateTimer?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val remaining = binding.viewModel?.updateTimer?.get() ?: 0L
                if (remaining > 0) {
                    viewModel.timeInMilliseconds.setRemainingTime(remaining)
                    viewModel.updateTimer.set(-1L)
                    startTimer()
                }
            }

        })
        startTimer()
    }

    private fun startTimer() {
        val service = Intent(this, CountdownService::class.java)
        service.putExtra(Constants.IntentNames.REMAINING_TIME, viewModel.timeInMilliseconds.getRemainingLong())
        startService(service)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(broadcastReceiver, IntentFilter(Constants.COUNTDOWN_BROADCAST))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onDestroy() {
        stopService(Intent(this, CountdownService::class.java))
        super.onDestroy()
    }
}
