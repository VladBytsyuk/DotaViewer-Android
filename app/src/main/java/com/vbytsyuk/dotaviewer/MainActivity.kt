package com.vbytsyuk.dotaviewer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vbytsyuk.dotaviewer.widgets.StatsView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { true }

        statsView.setOnClickListener {
            statsView.bind(
                StatsView.Data(
                    "",
                    "Alan Turing",
                    "Mathematician",
                    "10000 hours",
                    "69% winrate",
                    "KDA: 42"
                )
            )
        }
    }
}
