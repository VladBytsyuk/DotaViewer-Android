package com.vbytsyuk.dotaviewer.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View

class StatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    data class Data(
        val avatarUrl: String,
        val name: String,
        val rank: String,
        val time: String,
        val winrate: String,
        val kda: String
    )
}