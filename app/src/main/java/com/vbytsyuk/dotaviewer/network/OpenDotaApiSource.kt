package com.vbytsyuk.dotaviewer.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vbytsyuk.dataprovider.ApiResult
import com.vbytsyuk.dataprovider.PlayerDataSource
import com.vbytsyuk.domain.Player
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.opendota.com/api/"

class OpenDotaApiSource : PlayerDataSource {

    private val apiPlayer: ApiPlayer = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(ApiPlayer::class.java)


    override suspend fun getPlayer(accountId: String): ApiResult<Player> =
        try {
            val player = apiPlayer.getPlayerAsync(accountId).await()
            ApiResult.Success(player)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }

    override suspend fun savePlayer(player: Player) = throw UnsupportedOperationException(
        "Can't set Player data to OpenDotaApiSource. Use another PlayerDataSource."
    )
}