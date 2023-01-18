package com.tim.mediaapplication.ffmpeg

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.arthenica.ffmpegkit.FFmpegKit
import com.arthenica.ffmpegkit.FFmpegSessionCompleteCallback
import com.arthenica.ffmpegkit.ReturnCode
import com.tim.mediaapplication.R
import java.io.File

class FFmpegFragment : Fragment(R.layout.ffmpeg_fragment) {

    private lateinit var videoView: VideoView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoView = view.findViewById(R.id.videoPlayerFrame)
        view.findViewById<Button>(R.id.startBtn).setOnClickListener {
            start()
        }
    }

    private fun start() {
        runCatching {
            // IF VIDEO IS PLAYING STOP PLAYBACK
            videoView.stopPlayback()

            val outputFile = File(requireContext().cacheDir, "output.mp4").apply {
                if (exists()) {
                    delete()
                }
            }

            FFmpegKit.executeAsync("-i http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8 -t 10 -vf scale=320:180 /data/user/0/com.tim.mediaapplication/cache/output.mp4",
                FFmpegSessionCompleteCallback { session ->
                    if (ReturnCode.isSuccess(session.returnCode)) {
                        requireActivity().runOnUiThread {
                            videoView.setVideoURI(outputFile.toUri())

                            videoView.start()
                        }
                    }
                })
        }
    }
}