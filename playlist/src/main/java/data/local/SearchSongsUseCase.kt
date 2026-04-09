package data.local

import songs.repository.SongsRepository

class SearchSongsUseCase(
    private val repository: SongsRepository
) {
    operator fun invoke(query: String) =
        repository.searchSongs(query)
}
//
//class SearchSongsUseCase(
//    private val repository: SongsRepository
//) {
//
//    suspend fun hasLocalData(): Boolean {
//        return repository.hasLocalData()
//    }
//
//    operator fun invoke(query: String) =
//        repository.searchSongs(query)
//
//    suspend fun getRecentSongs() =
//        repository.getRecentSongs()
//}