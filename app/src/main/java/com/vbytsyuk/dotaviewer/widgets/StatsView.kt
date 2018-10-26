package com.vbytsyuk.dotaviewer.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_stats, this, true)
        textViews = listOf(name, rank, time, winrate, kda)
    }


    fun bind(data: Data) {
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