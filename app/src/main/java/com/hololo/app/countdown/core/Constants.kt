package com.hololo.app.countdown.core

object Constants {
    const val SECOND_IN_MILLISECONDS = 1000L
    const val MINUTEST_IN_MILLISECONDS = 60 * SECOND_IN_MILLISECONDS
    const val TWO_MINS_IN_MILLISECONDS = 2 * MINUTEST_IN_MILLISECONDS
    const val COUNTDOWN_INTERVAL = 5L

    const val COUNTDOWN_BROADCAST = "com.hololo.app.countdown.countdownservicebroadcast"

    object IntentNames {
        const val REMAINING_TIME = "remaining_time"
        const val COUNTDOWN_FINISH = "countdown_finish"
    }
}