package com.tim.mediaapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.ffmpegBtn).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_FFmpegFragment)
        }
        view.findViewById<Button>(R.id.exoBtn).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_exoFragment)
        }
        view.findViewById<Button>(R.id.vlcBtn).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_vlcFragment)
        }
    }
}