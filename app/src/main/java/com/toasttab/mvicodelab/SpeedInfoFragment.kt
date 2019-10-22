package com.toasttab.mvicodelab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.hannesdorfmann.mosby3.mvi.MviFragment
import com.jakewharton.rxrelay2.PublishRelay
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_speed_info.*

class SpeedInfoFragment : MviFragment<SpeedInfoView, SpeedInfoPresenter>(), SpeedInfoView {

    private val eventRelay = PublishRelay.create<SpeedInfoIntent>()

    override fun createPresenter(): SpeedInfoPresenter =
        SpeedInfoPresenter(InMemorySpeedInfoRepository())

    override fun render(speedInfoViewModel: SpeedInfoViewModel) {
        speed_info_seek_bar_speed.progress = speedInfoViewModel.speed
        speed_info_tv_speed.text = speedInfoViewModel.speed.toString()
        speed_info_btn_plus_ten.isEnabled = speedInfoViewModel.isIncrementEnabled
        speed_info_btn_minus_ten.isEnabled = speedInfoViewModel.isDecrementEnabled
        val url = when (speedInfoViewModel.closestMatchingCue) {
            VisualCue.ROCK -> "https://upload.wikimedia.org/wikipedia/commons/b/b4/Logan_Rock_Treen_closeup.jpg"
            VisualCue.SNAIL
            -> "https://upload.wikimedia.org/wikipedia/commons/6/69/Grapevinesnail_01.jpg"
            VisualCue.HUMAN -> "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Akha_cropped_hires.JPG/220px-Akha_cropped_hires.JPG"
            VisualCue.CAR -> "https://upload.wikimedia.org/wikipedia/commons/f/f1/Wiki_libra.jpg"
            VisualCue.FLIGHT -> "https://upload.wikimedia.org/wikipedia/commons/6/67/S7_Airlines_B767-33AER_%28VP-BVH%29_landing_at_Domodedovo_International_Airport.jpg"
            VisualCue.ROCKET -> "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Soyuz_TMA-9_launch.jpg/1200px-Soyuz_TMA-9_launch.jpg"
        }

        Picasso.get().load(url).into(speed_info_iv_visual_cue)
    }

    override fun events(): Observable<SpeedInfoIntent> = eventRelay

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_speed_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        speed_info_seek_bar_speed.max = MAX_SPEED
        speed_info_seek_bar_speed.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    eventRelay.accept(SliderMovedIntent(progress))
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        speed_info_btn_plus_ten.setOnClickListener {
            eventRelay.accept(PlusTenClickedIntent)
        }

        speed_info_btn_minus_ten.setOnClickListener {
            eventRelay.accept(MinusTenClickedIntent)
        }
    }
}
