package com.example.nhuviet.thucphamxanh.CustomView

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.EditText

import com.example.nhuviet.thucphamxanh.R

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by nhuvi on 16/05/2017.
 */

class PassWork_EditText : EditText {
    internal var eye: Drawable
    internal var eyestrike: Drawable
    internal var visible: Boolean? = false
    internal var useStrike: Boolean? = false
    internal var drawable: Drawable
    internal var usevalidata: Boolean? = false
    internal var ALPHA = (255 * .70f).toInt()
    internal var MATCHER_PATERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})"
    internal var pattern: Pattern
    internal var matcher: Matcher

    constructor(context: Context) : super(context) {
        khoitao(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        khoitao(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        khoitao(attrs)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        khoitao(attrs)
    }

    private fun khoitao(attributeSet: AttributeSet?) {

        this.pattern = Pattern.compile(MATCHER_PATERN)

        if (attributeSet != null) {
            val array = context.theme.obtainStyledAttributes(attributeSet, R.styleable.PassWord_EditText, 0, 0)
            this.useStrike = array.getBoolean(R.styleable.PassWord_EditText_useeyeTrike, false)
            this.usevalidata = array.getBoolean(R.styleable.PassWord_EditText_usevalidata, false)
        }
        eye = ContextCompat.getDrawable(context, R.drawable.ic_visibility_black_24dp).mutate()
        eyestrike = ContextCompat.getDrawable(context, R.drawable.ic_visibility_off_black_24dp).mutate()

        if (this.usevalidata!!) {
            onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    val chuoi = text.toString()
                    val textInputLayout = v.parentForAccessibility as TextInputLayout
                    matcher = pattern.matcher(chuoi)
                    if (!matcher.matches()) {
                        textInputLayout.isErrorEnabled = true
                        textInputLayout.error = "Mật khẩu bao gồm 6-20 kí tự, một số và một chữ hoa"
                    } else {
                        textInputLayout.isErrorEnabled = false
                        textInputLayout.error = ""
                    }
                }
            }
        }
        caidat()

    }

    private fun caidat() {
        inputType = InputType.TYPE_CLASS_TEXT or if (visible) InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD else InputType.TYPE_TEXT_VARIATION_PASSWORD
        val drawables = compoundDrawables
        drawable = if (useStrike!! && (!visible)!!) eyestrike else eye
        drawable.alpha = ALPHA
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[0], drawable, drawables[0])
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && event.x >= right - drawable.bounds.width()) {
            visible = (!visible)!!
            caidat()
            invalidate()
        }
        return super.onTouchEvent(event)
    }
}
