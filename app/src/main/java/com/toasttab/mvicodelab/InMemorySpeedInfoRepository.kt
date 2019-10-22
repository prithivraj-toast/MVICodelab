package com.toasttab.mvicodelab

class InMemorySpeedInfoRepository : SpeedInfoRepository {

    override fun getVisualCue(speed: Int): VisualCue {
        var cue = VisualCue.ROCK
        VisualCue.values().forEach {
            if (it.speed <= speed) {
                cue = it
            } else {
                return@forEach
            }
        }
        return cue
    }
}