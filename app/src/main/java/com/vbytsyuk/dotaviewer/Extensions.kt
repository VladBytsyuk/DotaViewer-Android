package com.vbytsyuk.dotaviewer

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

val appContext: Context
    get() = DotaViewerApp.applicationContext

val Int.resString: String
    get() = appContext.getString(this)

val Int.resColor: Int
    get() = ContextCompat.getColor(appContext, this)

val Int.resDrawable: Drawable?
    get() = ContextCompat.getDrawable(appContext, this)

val Int.resDimen: Float
    get() = appContext.resources.getDimension(this) / appContext.resources.displayMetrics.density


val Int.dp2px: Float
    get() = this * appContext.resources.displayMetrics.density
