package com.vbytsyuk.dotaviewer.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.vbytsyuk.dotaviewer.R
import kotlinx.android.synthetic.main.layout_edit_field.view.*

class EditField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var text: String
        get() = roundedEditField.currentText
        set(value) {
            roundedEditField.currentText = value
        }

    init {
        inflate(context, layoutId = R.layout.layout_edit_field)

        attrs?.read(context, style = R.styleable.EditField) { typedArray ->
            typedArray.getString(R.styleable.EditField_ef_hint)?.let {
                roundedEditField.hintText = it
                title.text = it
                if (it.isNotEmpty()) title.alpha = 0f
            }

            typedArray.getDrawable(R.styleable.EditField_ef_icon)?.let {
                roundedEditField.hintIcon = it
            }
        }

        roundedEditField.setOnTextChanged { currentText ->
            title.animateAlphaOnTextChange(currentText)
        }
    }


    private fun TextView.animateAlphaOnTextChange(text: String) = when {
        isVisible && text.isEmpty() -> startFadeOutAnimation()
        isNotVisible && text.isNotEmpty() -> startFadeInAnimation()
        else -> {}
    }

    private val View.isVisible get() = alpha == 1f
    private val View.isNotVisible get() = alpha == 0f

    private fun TextView.startFadeInAnimation() = startAlphaAnimation(to = 1f)
    private fun TextView.startFadeOutAnimation() = startAlphaAnimation(to = 0f)

    private fun TextView.startAlphaAnimation(to: Float) = animate()
        .alpha(to)
        .setDuration(300)
        .setInterpolator(DecelerateInterpolator())
        .start()
}