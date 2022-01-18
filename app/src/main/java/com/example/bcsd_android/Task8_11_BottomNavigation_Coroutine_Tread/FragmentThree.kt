package com.example.bcsd_android.Task8_11_BottomNavigation_Coroutine_Tread

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.FragmentThreeBinding
import kotlinx.coroutines.*
import java.util.*

class FragmentThree : Fragment() {
    private var mBinding: FragmentThreeBinding? = null
    private val binding get() = mBinding!!

    private var time: Int = 0
    private var timerTask: Timer? = null
    private var isRunning: Boolean = false
    private var minutes = 0

    private var labList = mutableListOf<StopwatchData>()
    val adapter = StopwatchAdapter(labList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentThreeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.stopwatchButtonStart.setOnClickListener {
            isRunning = !isRunning
            if (isRunning) { // 시작 버튼 클릭 시
                binding.stopwatchButtonLab.setBackgroundResource(R.drawable.ic_baseline_check_circle_enabled24)
                startTimer()
            } else { // 정지 버튼 클릭 시
                binding.stopwatchButtonLab.setBackgroundResource(R.drawable.ic_baseline_replay_24)
                pauseStopwatch()
            }
        }
        binding.stopwatchButtonLab.setOnClickListener {
            if (isRunning) { // 랩 버튼 클릭 시
                val manager = LinearLayoutManager(view.context)
                labList.add(StopwatchData(time % 100, time / 100, minutes))
                binding.stopwatchRecyclerView.adapter = adapter
                manager.reverseLayout = true
                manager.stackFromEnd = true
                binding.stopwatchRecyclerView.layoutManager = manager
            } else { // 초기화 버튼 클릭 시
                binding.stopwatchButtonLab.setBackgroundResource(R.drawable.ic_baseline_check_circle_disenabled24)
                isRunning = false
                labList.clear()
                binding.stopwatchRecyclerView.adapter = adapter
                binding.stopwatchRecyclerView.layoutManager = LinearLayoutManager(view.context)
                binding.stopwatchClockMillisecond.text = "00"
                binding.stopwatchClockSecond.text = "00"
                binding.stopwatchClockMinutes.text = "00"
                time = 0
                minutes = 0
            }
        }
    }

    private fun startTimer() {
        binding.stopwatchButtonStart.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24)
        binding.stopwatchButtonLab.setBackgroundResource(R.drawable.ic_baseline_check_circle_enabled24)
        binding.stopwatchButtonLab.isEnabled = true
        timerTask = kotlin.concurrent.timer(period = 10) {
            time++
            var millisecond = time % 100
            var second = time / 100
            if (second == 60) {
                time = 0
                millisecond = 0
                second = 0
                minutes++
            }
            startStopwatch(millisecond, second, minutes)
        }
    }

    private fun startStopwatch(millisecond: Int, second: Int, minutes: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val jobMillisecond = launch {
                if (millisecond < 10) {
                    binding.stopwatchClockMillisecond.text = "0" + millisecond
                } else {
                    binding.stopwatchClockMillisecond.text = millisecond.toString()
                }
            }.join()

            val jobSecond = launch {
                if (second < 10) {
                    binding.stopwatchClockSecond.text = "0" + second
                } else {
                    binding.stopwatchClockSecond.text = second.toString()
                }
            }.join()

            val jobMinutes = launch {
                if (minutes < 10) {
                    binding.stopwatchClockMinutes.text = "0" + minutes
                } else {
                    binding.stopwatchClockMinutes.text = minutes.toString()
                }
            }
        }
    }

    private fun pauseStopwatch() {
        binding.stopwatchButtonStart.setBackgroundResource(R.drawable.ic_baseline_not_started_24)
        timerTask?.cancel()
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}