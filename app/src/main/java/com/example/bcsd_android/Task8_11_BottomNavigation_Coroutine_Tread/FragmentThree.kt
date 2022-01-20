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
import java.text.SimpleDateFormat
import java.util.*

class FragmentThree : Fragment() {
    private var mBinding: FragmentThreeBinding? = null
    private val binding get() = mBinding!!

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

        stopwatchPlayer()
    }

    private fun stopwatchPlayer() {
        val scope = CoroutineScope(Dispatchers.Main)
        var isRunning = false
        var countTime: Long = 0

        binding.stopwatchButtonStart.setOnClickListener { // 시작 버튼 클릭 시 이벤트
            val mainTime = System.currentTimeMillis()
            isRunning = !isRunning
            val job: Job = scope.launch {
                val scopeTime = System.currentTimeMillis()
                if (isRunning) {
                    binding.stopwatchButtonLab.isEnabled = true
                    binding.stopwatchButtonLab.setBackgroundResource(R.drawable.ic_baseline_check_circle_enabled24)
                    binding.stopwatchButtonStart.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24)
                    while (isRunning) {
                        countTime += scopeTime - mainTime
                        val simpleCountTime = SimpleDateFormat("mm:ss:SS").format(countTime)
                        binding.stopwatchButtonLab.setOnClickListener { // 타이머 중 랩 버튼 클릭 시 이벤트
                            val manager = LinearLayoutManager(view?.context)
                            labList.add(StopwatchData(countTime))
                            manager.reverseLayout = true
                            manager.stackFromEnd = true
                            binding.stopwatchRecyclerView.layoutManager = manager
                            binding.stopwatchRecyclerView.adapter = adapter
                        }
                        binding.stopwatchClockTime.text = simpleCountTime
                        delay(10)
                    }
                } else { // 정지 버튼 클릭 시 이벤트
                    binding.stopwatchButtonStart.setBackgroundResource(R.drawable.ic_baseline_not_started_24)
                    binding.stopwatchButtonLab.setBackgroundResource(R.drawable.ic_baseline_replay_24)
                    binding.stopwatchButtonLab.setOnClickListener { // 초기화 버튼 클릭 시 이벤트
                        binding.stopwatchButtonLab.setBackgroundResource(R.drawable.ic_baseline_check_circle_disenabled24)
                        binding.stopwatchButtonLab.isEnabled = false
                        binding.stopwatchClockTime.text = "00:00:00"
                        countTime = 0
                        labList.clear()
                        binding.stopwatchRecyclerView.adapter = adapter
                    }

                }
            }
        }
    }


    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}