package com.vbytsyuk.dataprovider

import com.vbytsyuk.domain.Player

class PlayerRepository(private val playerDataSource: PlayerDataSource) {
    suspend fun getPlayer(accountId: String): ApiResult<Player> =
        playerDataSource.getPlayer(accountId)

    suspend fun savePlayer(player: Player) =
        playerDataSource.savePlayer(player)
}

interface PlayerDataSource {
    suspend fun getPlayer(accountId: String): ApiResult<Player>
    suspend fun savePlayer(player: Player)
}
