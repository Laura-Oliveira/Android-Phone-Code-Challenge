package com.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val artwork: String,
    val previewUrl: String
) : Parcelable {
    val highQualityImage = artwork.replace("100x100", "300x300")
}