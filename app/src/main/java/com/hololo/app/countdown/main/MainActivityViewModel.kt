package com.hololo.app.countdown.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.hololo.app.countdown.core.Constants
import com.hololo.app.countdown.pojo.RemainingTimeModel

class MainActivityViewModel() : ViewModel() {
    val timeInMilliseconds = RemainingTimeModel(Constants.TWO_MINS_IN_MILLISECONDS)
    val finishTimer = ObservableField<Boolean>(false)
    val updateTimer = ObservableField<Long>(-1L)

    fun addTenSeconds() {
        var remaining = timeInMilliseconds.getRemainingLong()
        remaining = remaining.plus(10 * Constants.SECOND_IN_MILLISECONDS)

        if (remaining > Constants.TWO_MINS_IN_MILLISECONDS) {
            remaining = Constants.TWO_MINS_IN_MILLISECONDS
        }
        updateTimer.set(remaining)
    }

}