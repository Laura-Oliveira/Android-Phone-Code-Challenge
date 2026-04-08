package data.mapper


import data.model.Song
import data.model.SongEntity

fun SongEntity.toDomain(): Song {
    return Song(
        id = id,
        title = title,
        artist = artist,
        artwork = artwork
    )
}