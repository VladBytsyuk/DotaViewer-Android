package com.vbytsyuk.dotaviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vbytsyuk.dotaviewer.widgets.StatsView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { true }

        val testStatsViewData = StatsView.Data(
            avatarUrl = "",
            name = "Alan Turing",
            rank = "Mathematician",
            time = "10000 hours",
            winrate = "69% winrate",
            kda = "KDA: 42"
        )
        statsView.setOnClickListener {
            statsView.bind(testStatsViewData)
        }
    }
}
