package com.toasttab.mvicodelab

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter

class SpeedInfoPresenter(private val speedInfoRepository: SpeedInfoRepository) :
    MviBasePresenter<SpeedInfoView, SpeedInfoViewModel>() {

    override fun bindIntents() {
        val intents = intent(SpeedInfoView::events)
        val initialState = SpeedInfoViewModel(
            speed = 0,
            closestMatchingCue = VisualCue.ROCK,
            isIncrementEnabled = true,
            isDecrementEnabled = false
        )
        val stateToObserve = intents.scan(initialState, this::reduce)

        subscribeViewState(stateToObserve, SpeedInfoView::render)
    }

    private fun reduce(
        currentViewModel: SpeedInfoViewModel,
        intent: SpeedInfoIntent
    ): SpeedInfoViewModel {
        return when (intent) {
            is SliderMovedIntent -> {
                currentViewModel.copy(
                    speed = intent.value,
                    closestMatchingCue = speedInfoRepository.getVisualCue(intent.value),
                    isIncrementEnabled = intent.value != MAX_SPEED,
                    isDecrementEnabled = intent.value != MIN_SPEED
                )
            }
            PlusTenClickedIntent -> {
                var newSpeed = currentViewModel.speed + 10
                if (newSpeed > MAX_SPEED) {
                    newSpeed = MAX_SPEED
                }
                currentViewModel.copy(
                    speed = newSpeed,
                    closestMatchingCue = speedInfoRepository.getVisualCue(newSpeed),
                    isIncrementEnabled = newSpeed != MAX_SPEED,
                    isDecrementEnabled = newSpeed != MIN_SPEED
                )
            }

            MinusTenClickedIntent -> {
                var newSpeed = currentViewModel.speed - 10
                if (newSpeed < MIN_SPEED) {
                    newSpeed = MIN_SPEED
                }
                currentViewModel.copy(
                    speed = newSpeed,
                    closestMatchingCue = speedInfoRepository.getVisualCue(newSpeed),
                    isIncrementEnabled = newSpeed != MAX_SPEED,
                    isDecrementEnabled = newSpeed != MIN_SPEED
                )
            }
        }
    }
}
