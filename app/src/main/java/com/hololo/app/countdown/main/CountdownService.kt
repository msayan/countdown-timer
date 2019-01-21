package com.hololo.app.countdown.main

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import com.hololo.app.countdown.core.Constants

class CountdownService : Service() {
    var timer: CountDownTimer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startTimer(intent?.getLongExtra(Constants.IntentNames.REMAINING_TIME, 0L) ?: 0)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startTimer(remaining: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(remaining, Constants.COUNTDOWN_INTERVAL) {
            override fun onFinish() {
                val intent = Intent(Constants.COUNTDOWN_BROADCAST)
                intent.putExtra(Constants.IntentNames.COUNTDOWN_FINISH, true)
                sendBroadcast(intent)
            }

            override fun onTick(millisUntilFinished: Long) {
                val intent = Intent(Constants.COUNTDOWN_BROADCAST)
                intent.putExtra(Constants.IntentNames.REMAINING_TIME, millisUntilFinished)
                sendBroadcast(intent)
            }
        }

        timer?.start()
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

}