package com.vbytsyuk.dotaviewer

import com.vbytsyuk.dataprovider.SteamDataSource

const val SHARED_PREFERENCE_NAME = "SharedPreferenceSource"
const val STEAM_ID_NAME = "SteamID"

class SharedPreferencesSource : SteamDataSource {
    override var steamID: String
        get() = storage.getString(STEAM_ID_NAME, "") ?: ""
        set(new) = storage.put(STEAM_ID_NAME, new)


    private val storage = DotaViewerApp.instance.getSharedPreferences(SHARED_PREFERENCE_NAME, 0)
}