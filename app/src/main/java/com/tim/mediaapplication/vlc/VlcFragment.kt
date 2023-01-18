package com.tim.mediaapplication.vlc

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tim.mediaapplication.R
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout

class VlcFragment : Fragment(R.layout.vlc_fragment) {

    private lateinit var libVlc: LibVLC
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var vlcPlayer: VLCVideoLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        libVlc = LibVLC(requireContext())
        mediaPlayer = MediaPlayer(libVlc)
        vlcPlayer = view.findViewById(R.id.vlcPlayer)
        mediaPlayer.attachViews(vlcPlayer, null, false, false)
        mediaPlayer.play(Uri.parse("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8"))

    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
        mediaPlayer.detachViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        libVlc.release()
    }
}