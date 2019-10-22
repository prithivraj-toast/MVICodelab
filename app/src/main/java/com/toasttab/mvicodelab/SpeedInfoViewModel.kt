package com.toasttab.mvicodelab

/**
 * The view model is immutable which not only reduces cognitive complexity, but also allows us to do extra processing in background threads without locking.
 * Kotlin data classes are particularly important for larger view models.
 * The more complex your view is, the larger the view model.
 * Without the built in copy function of data classes, MVI would be much more difficult to implement.
 * This function allows us to specifically change a single part of a view model, while keeping the rest of the model the same.
 * This is perfect for reducing view models based on a specific intent.
 *
 * View models should be simple objects with no logic so they do not need their own unit tests.
 * However, in special cases your view model may be a bit complex and you may want to have derived fields on your view model.
 * This means introducing some simple logic into your view model which should be unit tested.
 */

data class SpeedInfoViewModel(
    val speed: Int,
    val closestMatchingCue: VisualCue,
    val isIncrementEnabled: Boolean,
    val isDecrementEnabled: Boolean
)

enum class VisualCue(val speed: Int) {
    ROCK(0),
    SNAIL(10),
    HUMAN(20),
    CAR(50),
    FLIGHT(70),
    ROCKET(80)
}

const val MIN_SPEED = 0
const val MAX_SPEED = 100