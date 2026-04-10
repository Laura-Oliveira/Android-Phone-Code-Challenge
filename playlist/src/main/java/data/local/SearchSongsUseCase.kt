package data.local

import songs.repository.SongsRepository

class SearchSongsUseCase(
    private val repository: SongsRepository
) {
    operator fun invoke(query: String) =
        repository.searchSongs(query)
}