package com.vbytsyuk.dotaviewer.widgets

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.vbytsyuk.dotaviewer.DotaViewerApp


val appContext: Context get() = DotaViewerApp.instance.applicationContext


fun ViewGroup.inflate(
    context: Context = appContext,
    @LayoutRes layoutId: Int
) = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
    .inflate(layoutId, this, true)!!


fun AttributeSet.read(
    context: Context = appContext,
    style: IntArray,
    reader: (TypedArray) -> Unit
) {
    val typedArray =
        context.obtainStyledAttributes(this, style, 0, 0)
    try {
        reader(typedArray)
    } finally {
        typedArray.recycle()
    }
}