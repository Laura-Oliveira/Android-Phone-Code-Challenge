package com.model

data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val artwork: String,
    val previewUrl: String
) {
    val highQualityImage = artwork.replace("100x100", "300x300")
}