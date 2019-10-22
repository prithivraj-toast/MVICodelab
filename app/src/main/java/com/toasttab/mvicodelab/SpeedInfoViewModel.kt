package com.toasttab.mvicodelab

data class SpeedInfoViewModel(
    val speed: Int,
    val closestMatchingCue: VisualCue,
    val isIncrementEnabled: Boolean,
    val isDecrementEnabled: Boolean
)

enum class VisualCue(val speed : Int) {
    ROCK(0),
    SNAIL(10),
    HUMAN(20),
    CAR(50),
    FLIGHT(70),
    ROCKET(80)
}

const val MIN_SPEED = 0
const val MAX_SPEED = 100