package data.remote


data class ItunesResponse(
    val resultCount: Int,
    val results: List<data.local.ItunesSongDto>
)