package com.vbytsyuk.dotaviewer.shared_preferences

import com.vbytsyuk.dataprovider.SteamDataSource
import com.vbytsyuk.dotaviewer.DotaViewerApp
import com.vbytsyuk.dotaviewer.put

const val SHARED_PREFERENCE_NAME = "SharedPreferenceSource"
const val STEAM_ID_NAME = "SteamID"

class SharedPreferencesSource : SteamDataSource {
    override var steamID: String
        get() = storage.getString(STEAM_ID_NAME, "") ?: ""
        set(new) = storage.put(STEAM_ID_NAME, new)


    private val storage = DotaViewerApp.instance.getSharedPreferences(
        SHARED_PREFERENCE_NAME, 0)
}