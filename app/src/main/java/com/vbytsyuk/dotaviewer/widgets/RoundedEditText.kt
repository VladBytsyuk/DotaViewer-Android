package com.vbytsyuk.dotaviewer.widgets

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.vbytsyuk.dotaviewer.R
import com.vbytsyuk.dotaviewer.setOnTextChangedListener
import kotlinx.android.synthetic.main.rounded_edit_text.view.*


class RoundedEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var currentText: String
        get() = textField.text.toString()
        set(value) { textField.setText(value) }

    var hintText: String?
        get() = textField.hint.toString()
        set(value) { textField.hint = value }

    var hintIcon: Drawable?
        get() = icon.drawable
        set(value) { icon.setImageDrawable(value) }

    init {
        inflate(context, layoutId = R.layout.rounded_edit_text)

        attrs?.read(context, style = R.styleable.RoundedEditText) { typedArray ->
            hintText = typedArray.getString(R.styleable.RoundedEditText_ret_hint)
            hintIcon = typedArray.getDrawable(R.styleable.RoundedEditText_ret_icon)
        }
    }


    public fun setOnTextChanged(onTextChanged: (currentText: String) -> Unit) {
        textField.setOnTextChangedListener { onTextChanged(it) }
    }
}