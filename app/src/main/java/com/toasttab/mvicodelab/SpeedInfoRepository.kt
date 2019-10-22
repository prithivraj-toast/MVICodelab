package com.toasttab.mvicodelab

interface SpeedInfoRepository {
    fun getVisualCue(speed: Int): VisualCue
}