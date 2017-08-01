package com.example.nhuviet.thucphamxanh.CustomView

import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText

import com.example.nhuviet.thucphamxanh.R

/**
 * Created by nhuvi on 16/05/2017.
 */

class Clear_EditText : EditText {

    internal var crossx: Drawable
    internal var nonecrossx: Drawable
    internal var visible: Boolean? = false
    internal var drawable: Drawable

    constructor(context: Context) : super(context) {
        khoitao()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        khoitao()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        khoitao()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        khoitao()
    }

    private fun khoitao() {
        crossx = ContextCompat.getDrawable(context, R.drawable.ic_clear_black_24dp).mutate()
        nonecrossx = ContextCompat.getDrawable(context, android.R.drawable.screen_background_light_transparent).mutate()
        cauhinh()
    }

    private fun cauhinh() {
        inputType = InputType.TYPE_CLASS_TEXT
        val drawables = compoundDrawables
        drawable = if (visible) crossx else nonecrossx
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3])
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (MotionEvent.ACTION_DOWN == event.action && event.x >= right - drawable.bounds.width()) {
            setText("")
        }
        return super.onTouchEvent(event)

    }

    override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (lengthAfter == 0 && start == 0) {
            visible = false
            cauhinh()
        } else {
            visible = true
            cauhinh()

        }
    }
}
