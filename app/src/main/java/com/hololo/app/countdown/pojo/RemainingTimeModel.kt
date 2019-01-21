package com.hololo.app.countdown.pojo

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.hololo.app.countdown.BR
import com.hololo.app.countdown.core.Constants

class RemainingTimeModel() : BaseObservable() {
    private var minutes: Long = 0L
    private var seconds: Long = 0L
    private var milliseconds: Long = 0L

    constructor(remaining: Long) : this() {
        setRemainingTime(remaining)
    }

    fun setRemainingTime(remaining: Long) {
        minutes = remaining.div(Constants.SECOND_IN_MILLISECONDS.times(60))
        seconds = (remaining % Constants.SECOND_IN_MILLISECONDS.times(60)).div(Constants.SECOND_IN_MILLISECONDS)
        milliseconds = remaining % Constants.SECOND_IN_MILLISECONDS.div(100)

        notifyPropertyChanged(BR.remainingString)
    }

    fun getRemainingLong(): Long {
        return minutes.times(Constants.MINUTEST_IN_MILLISECONDS)
            .plus(seconds.times(Constants.SECOND_IN_MILLISECONDS))
            .plus(milliseconds)
    }

    @Bindable
    fun getRemainingString(): String {
        return if (minutes > 0) {
            "$minutes : $seconds : $milliseconds"
        } else {
            "$seconds : $milliseconds"
        }
    }
}