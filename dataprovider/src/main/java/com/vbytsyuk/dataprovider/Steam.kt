package com.vbytsyuk.dataprovider

class SteamRepository(private val steamDataSource: SteamDataSource) {
    var steamID: String
        get() = steamDataSource.steamID
        set(new) {
            steamDataSource.steamID = new
        }
}

interface SteamDataSource {
    var steamID: String
}
