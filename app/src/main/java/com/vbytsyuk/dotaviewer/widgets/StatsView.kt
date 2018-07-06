package com.vbytsyuk.dotaviewer.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View

class StatsView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : View(context, attrs, defStyleAttr, defStyleRes) {

    data class Data(val avatarUrl: String,
                    val name: String,
                    val rank: String,
                    val time: String,
                    val winrate: String,
                    val kda: String)
}