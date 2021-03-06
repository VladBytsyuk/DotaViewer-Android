package com.vbytsyuk.dotaviewer

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.content.ContextCompat

val appContext: Context get() = DotaViewerApp.instance.applicationContext

val Int.resString: String get() = appContext.getString(this)
val Int.resColor: Int get() = ContextCompat.getColor(appContext, this)
val Int.resDrawable: Drawable? get() = ContextCompat.getDrawable(appContext, this)
val Int.resDimen: Float
    get() = appContext.resources.getDimension(this) / appContext.resources.displayMetrics.density


val Int.dp2px: Float get() = this * appContext.resources.displayMetrics.density

val EditText.inputText: String get() = text.toString()

fun EditText.setOnTextChangedListener(listener: (String) -> Unit) =
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =
            listener(p0.toString())
    })

fun SharedPreferences.put(key: String, value: Any) = edit().run {
    when (value) {
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is Long -> putLong(key, value)
        is Float -> putFloat(key, value)
        else -> error("Only primitive types can be stored in SharedPreferences")
    }
    apply()
}

val <T> Collection<T>.snapshot: List<T> get() = ArrayList(this)

val Any.tag: String get() = javaClass.name