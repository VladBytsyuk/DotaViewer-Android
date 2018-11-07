package com.vbytsyuk.dotaviewer.network

import com.vbytsyuk.domain.Player
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiPlayer {
    @GET("players/{account_id}")
    fun getPlayer(
        @Path("account_id") accountId: String
    ): Deferred<Player>
}