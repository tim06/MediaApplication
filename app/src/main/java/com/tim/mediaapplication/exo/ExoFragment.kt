package com.tim.mediaapplication.exo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.MimeTypes
import com.tim.mediaapplication.R
import com.tim.mediaapplication.createMockVideo


class ExoFragment : Fragment(R.layout.exo_fragment) {

    private lateinit var player: ExoPlayer
    private val onLoadListener: Player.Listener = object : Player.Listener {
        override fun onIsLoadingChanged(isLoading: Boolean) {
            super.onIsLoadingChanged(isLoading)
            Log.d("qwewqe", "Is loaded: $isLoading")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player = ExoPlayer.Builder(requireContext()).build()
        val playerView = view.findViewById<StyledPlayerView>(R.id.playerView)
        playerView.player = player

        val videoUri = "https://assets.afcdn.com/video49/20210722/v_645516.m3u8"
        val mediaItem: MediaItem = MediaItem.Builder().setUri(videoUri).build()
        player.addListener(onLoadListener)

        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.removeListener(onLoadListener)
        player.release()
    }
}