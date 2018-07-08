package com.vbytsyuk.dotaviewer.widgets

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.view.LayoutInflater

class StatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_stats, this, true)
    }
    data class Data(
        val avatarUrl: String,
        val name: String,
        val rank: String,
        val time: String,
        val winrate: String,
        val kda: String
    )
}