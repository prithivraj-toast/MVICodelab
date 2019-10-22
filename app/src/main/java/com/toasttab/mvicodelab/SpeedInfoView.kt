package com.toasttab.mvicodelab

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface SpeedInfoView : MvpView {

    fun render(speedInfoViewModel: SpeedInfoViewModel)

    fun events(): Observable<SpeedInfoIntent>
}
