package com.network.remote

import com.network.model.ItunesSongDto

data class ItunesResponse(
    val resultCount: Int,
    val results: List<ItunesSongDto>
)