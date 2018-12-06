package com.vbytsyuk.dotaviewer

import com.vbytsyuk.dataprovider.SteamDataSource

const val LIL_STEAM_ID = "106809101"

class StubSource : SteamDataSource {
    override var steamID: String = ""
        get() = LIL_STEAM_ID
}