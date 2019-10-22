package com.toasttab.mvicodelab

sealed class SpeedInfoIntent

data class SliderMovedIntent(val value: Int): SpeedInfoIntent()
object PlusTenClickedIntent: SpeedInfoIntent()
object MinusTenClickedIntent: SpeedInfoIntent()