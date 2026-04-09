package data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import data.model.Song

class SongsPagingSource(
    private val api: ItunesAPI,
    private val query: String
) : PagingSource<Int, Song>()
{
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song>
    {
        return try {
            val offset = params.key ?: 0
            val response = api.searchSongs(
                term = query,
                offset = offset,
                limit = params.loadSize
            )

            val songs = response.results.map {

                val highQualityImage = it.artworkUrl100
                    .replace("100x100", "300x300")

                Song(
                    id = it.trackId,
                    title = it.trackName,
                    artist = it.artistName,
                    artwork = highQualityImage
                )
            }

            LoadResult.Page(
                data = songs,
                prevKey = if (offset == 0) null else offset - params.loadSize,
                nextKey = if (songs.isEmpty()) null else offset + params.loadSize
            )

        } catch (e: Exception)
        { LoadResult.Error(e) }
    }

    override fun getRefreshKey(state: PagingState<Int, Song>): Int?
    { return state.anchorPosition }
}