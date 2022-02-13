package com.example.bcsd_android.task12mvvm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bcsd_android.BaseFragment
import com.example.bcsd_android.R
import com.example.bcsd_android.databinding.Task12FragmentWebBinding

class FragmentWeb : BaseFragment<Task12FragmentWebBinding>(R.layout.task12_fragment_web) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intentUrl = Intent(Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com")))

        startUrl(intentUrl)
    }

    private fun startUrl(intentUrl: Intent) {
        binding.webNaverButton.setOnClickListener {
            startActivity(intentUrl)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}