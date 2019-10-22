package com.toasttab.mvicodelab

import com.toasttab.mvicodelab.VisualCue.CAR
import com.toasttab.mvicodelab.VisualCue.FLIGHT
import com.toasttab.mvicodelab.VisualCue.HUMAN
import com.toasttab.mvicodelab.VisualCue.ROCK
import com.toasttab.mvicodelab.VisualCue.ROCKET
import com.toasttab.mvicodelab.VisualCue.SNAIL
import io.kotlintest.shouldBe
import org.junit.Test

class InMemorySpeedInfoRepositoryTest {

    private val inMemorySpeedInfoRepository = InMemorySpeedInfoRepository()

    @Test
    fun `getVisualCue computes visual cues correctly`() {
        with(inMemorySpeedInfoRepository) {
            for (i in 0 until 10) {
                getVisualCue(i) shouldBe ROCK
            }
            for (i in 10 until 20) {
                getVisualCue(i) shouldBe SNAIL
            }
            for (i in 20 until 50) {
                getVisualCue(i) shouldBe HUMAN
            }
            for (i in 50 until 70) {
                getVisualCue(i) shouldBe CAR
            }
            for (i in 70 until 80) {
                getVisualCue(i) shouldBe FLIGHT
            }
            for (i in 80 until 100) {
                getVisualCue(i) shouldBe ROCKET
            }
        }
    }
}