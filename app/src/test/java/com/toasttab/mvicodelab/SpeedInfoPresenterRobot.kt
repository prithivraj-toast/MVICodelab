package com.toasttab.mvicodelab

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

class SpeedInfoPresenterRobot(speedInfoPresenter: SpeedInfoPresenter) {

    val renderEvents = mutableListOf<SpeedInfoViewModel>()
    private val eventRelay = PublishRelay.create<SpeedInfoIntent>()
    private val speedInfoView = object : SpeedInfoView {
        override fun render(speedInfoViewModel: SpeedInfoViewModel) {
            renderEvents.add(speedInfoViewModel)
        }

        override fun events(): Observable<SpeedInfoIntent> = eventRelay
    }

    init {
        speedInfoPresenter.attachView(speedInfoView)
    }

    fun moveSlider(newValue: Int) {
        eventRelay.accept(SliderMovedIntent(newValue))
    }

    fun clickMinusButton() {
        eventRelay.accept(MinusTenClickedIntent)
    }

    fun clickPlusButton(){
        eventRelay.accept(PlusTenClickedIntent)
    }
}