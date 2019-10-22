package com.toasttab.mvicodelab

/**
 * An intent is an object that represents a single type of user interaction.
 */

sealed class SpeedInfoIntent

data class SliderMovedIntent(val value: Int) : SpeedInfoIntent()
object PlusTenClickedIntent : SpeedInfoIntent()
object MinusTenClickedIntent : SpeedInfoIntent()