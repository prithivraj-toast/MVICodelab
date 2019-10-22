package com.toasttab.mvicodelab

import com.toasttab.mvicodelab.VisualCue.HUMAN
import com.toasttab.mvicodelab.VisualCue.ROCK
import com.toasttab.mvicodelab.VisualCue.ROCKET
import io.kotlintest.shouldBe
import org.junit.Before
import org.junit.Test

class SpeedInfoTests {

    private lateinit var speedInfoPresenterRobot: SpeedInfoPresenterRobot

    @Before
    fun setup() {
        val speedInfoPresenter = SpeedInfoPresenter(InMemorySpeedInfoRepository())
        speedInfoPresenterRobot = SpeedInfoPresenterRobot(speedInfoPresenter)
    }

    @Test
    fun `Sliding to zero updates UI correctly`() {
        speedInfoPresenterRobot.moveSlider(0)
        val speedInfoViewModel = speedInfoPresenterRobot.renderEvents.last()
        speedInfoViewModel.speed shouldBe 0
        speedInfoViewModel.closestMatchingCue shouldBe ROCK
        speedInfoViewModel.isIncrementEnabled shouldBe true
        speedInfoViewModel.isDecrementEnabled shouldBe false
    }

    @Test
    fun `Sliding to 100 updates UI correctly`() {
        speedInfoPresenterRobot.moveSlider(100)
        val speedInfoViewModel = speedInfoPresenterRobot.renderEvents.last()
        speedInfoViewModel.speed shouldBe 100
        speedInfoViewModel.closestMatchingCue shouldBe ROCKET
        speedInfoViewModel.isIncrementEnabled shouldBe false
        speedInfoViewModel.isDecrementEnabled shouldBe true
    }

    @Test
    fun `Sliding updates UI correctly`() {
        speedInfoPresenterRobot.moveSlider(30)
        val speedInfoViewModel = speedInfoPresenterRobot.renderEvents.last()
        speedInfoViewModel.speed shouldBe 30
        speedInfoViewModel.closestMatchingCue shouldBe HUMAN
        speedInfoViewModel.isIncrementEnabled shouldBe true
        speedInfoViewModel.isDecrementEnabled shouldBe true
    }

    @Test
    fun `Decrementing with button updates UI correctly`() {
        speedInfoPresenterRobot.moveSlider(30)
        speedInfoPresenterRobot.clickMinusButton()
        val speedInfoViewModel = speedInfoPresenterRobot.renderEvents.last()
        speedInfoViewModel.speed shouldBe 20
        speedInfoViewModel.closestMatchingCue shouldBe HUMAN
        speedInfoViewModel.isIncrementEnabled shouldBe true
        speedInfoViewModel.isDecrementEnabled shouldBe true
    }

    @Test
    fun `Decrementing with button when speed is between 1-10 updates UI correctly`() {
        speedInfoPresenterRobot.moveSlider(7)
        speedInfoPresenterRobot.clickMinusButton()
        val speedInfoViewModel = speedInfoPresenterRobot.renderEvents.last()
        speedInfoViewModel.speed shouldBe 0
        speedInfoViewModel.closestMatchingCue shouldBe ROCK
        speedInfoViewModel.isIncrementEnabled shouldBe true
        speedInfoViewModel.isDecrementEnabled shouldBe false
    }

    @Test
    fun `Incrementing with button updates UI correctly`() {
        speedInfoPresenterRobot.moveSlider(30)
        speedInfoPresenterRobot.clickPlusButton()
        val speedInfoViewModel = speedInfoPresenterRobot.renderEvents.last()
        speedInfoViewModel.speed shouldBe 40
        speedInfoViewModel.closestMatchingCue shouldBe HUMAN
        speedInfoViewModel.isIncrementEnabled shouldBe true
        speedInfoViewModel.isDecrementEnabled shouldBe true
    }

    @Test
    fun `Incrementing with button when speed is between 90-100 updates UI correctly`() {
        speedInfoPresenterRobot.moveSlider(97)
        speedInfoPresenterRobot.clickPlusButton()
        val speedInfoViewModel = speedInfoPresenterRobot.renderEvents.last()
        speedInfoViewModel.speed shouldBe 100
        speedInfoViewModel.closestMatchingCue shouldBe ROCKET
        speedInfoViewModel.isIncrementEnabled shouldBe false
        speedInfoViewModel.isDecrementEnabled shouldBe true
    }
}
