package com.tim.mediaapplication

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import java.io.File

fun Context.createMockVideo(): Uri {
    val fileInAssets = assets.open("sample.mp4")

    val initialFile = File(cacheDir, "sample.mp4")
    if (initialFile.exists()) {
        initialFile.delete()
    }
    initialFile.outputStream().use {
        fileInAssets.copyTo(it)
    }
    return initialFile.toUri()
}