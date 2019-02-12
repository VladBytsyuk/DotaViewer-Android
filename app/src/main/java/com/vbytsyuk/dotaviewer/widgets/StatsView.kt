package com.vbytsyuk.dotaviewer.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.resDrawable
import kotlinx.android.synthetic.main.view_stats.view.*


class StatsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val textViews: List<TextView>

    init {
        inflate(context, layoutId = R.layout.view_stats)
        textViews = listOf(name, rank, time, winrate, kda)
    }


    fun bind(data: Data) {
        Glide.with(context)
            .load(data.avatarUrl)
            .into(icon)
        name.text = data.name
        rank.text = data.rank
        time.text = data.time
        winrate.text = data.winrate
        kda.text = data.kda
        loadingDone()
    }


    private fun loadingDone() {
        icon.background = null
        icon.setImageDrawable(R.mipmap.avatar.resDrawable)
        textViews.forEach {
            it.background = null
        }
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