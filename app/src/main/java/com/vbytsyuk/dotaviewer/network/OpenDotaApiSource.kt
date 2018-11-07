package com.vbytsyuk.dotaviewer.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.vbytsyuk.dataprovider.PlayerDataSource
import com.vbytsyuk.domain.Player
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.opendota.com/api"

class OpenDotaApiSource : PlayerDataSource {
    private val retrofit: ApiPlayer = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(ApiPlayer::class.java)

    override var player: Player
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {
            throw UnsupportedOperationException("Can't set Player data to OpenDotaApiSource. Use another PlayerDataSource.")
        }
}