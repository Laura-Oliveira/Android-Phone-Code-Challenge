package data.local

data class ItunesSongDto(
    val trackId: Long,
    val trackName: String,
    val artistName: String,
    val previewUrl: String,
    val artworkUrl100: String
)