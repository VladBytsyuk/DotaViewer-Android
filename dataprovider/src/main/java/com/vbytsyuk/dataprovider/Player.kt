package com.vbytsyuk.dataprovider

import com.vbytsyuk.domain.Player

class PlayerRepository(private val playerDataSource: PlayerDataSource) {
    var player: Player
        get() = playerDataSource.player
        set(new) {
            playerDataSource.player = new
        }
}

interface PlayerDataSource {
    var player: Player
}
