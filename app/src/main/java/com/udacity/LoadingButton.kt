package com.udacity

import android.animation.*
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class LoadingButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var widthSize = 0
    private var heightSize = 0
    private var mArcBounds = RectF(50F, 20F, 100F, 80F)
    private var valueAnimator = ValueAnimator()
    private var buttonText = resources.getString(R.string.download)
    private var endAngle = 0F
    private var starAngle = 0F
    private var setTheTextSize = resources.getDimension(R.dimen.textSize)
    private var clipRectRight = resources.getDimension(R.dimen.RectRight)
    private var clipRectBottom = resources.getDimension(R.dimen.RectBottom)
    private var clipRectTop = resources.getDimension(R.dimen.RectTop)
    private var clipRectLeft = resources.getDimension(R.dimen.RectLeft)

    /// *****  Paint  **** //////
    private val paintCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = resources.getColor(R.color.colorPrimary)
        textAlign = Paint.Align.CENTER
    }
    private val paintRectangle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = context.getColor(R.color.colorPrimaryDark)
        textAlign = Paint.Align.CENTER
    /*mohamed elgohary*/
    }
    private var paintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = resources.getColor(R.color.colorPrimary)
        textAlign = Paint.Align.CENTER
        textSize = setTheTextSize
    }


    /// *****  ButtonState  **** //////
    private var buttonState: ButtonState by Delegates.observable<ButtonState>(ButtonState.Completed) { p, old, new ->

        buttonText = context.getString(R.string.download)
        if (new == ButtonState.Loading) {
            val updateListener = ValueAnimator.AnimatorUpdateListener {
                buttonText = resources.getString(R.string.loading)
                endAngle = (it.animatedValue as Float)
                clipRectRight = (it.animatedValue as Float)
                invalidate()
            }
            /*mohamed elgohary*/
            valueAnimator =
                AnimatorInflater.loadAnimator(context, R.animator.my_animation) as ValueAnimator
            valueAnimator.addUpdateListener(updateListener)
            valueAnimator.start()
            invalidate()
        } else if (buttonState == ButtonState.Completed){
            buttonText = context.getString(R.string.download)
            valueAnimator.cancel()
            invalidate()
        }else{
            buttonText = context.getString(R.string.download)
            valueAnimator.cancel()
            invalidate()
        }
    }

    init {
        buttonText = context.getString(R.string.download)
    }
    /// *****  onDraw  **** //////
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawDownloadButton(canvas!!)
        canvas.save()
    }

    /// *****  Set State on Button  **** //////
    fun setState(state: ButtonState) {
        buttonState = state
    }

    /// *****  onMeasure  **** //////
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minw: Int = paddingLeft + paddingRight + suggestedMinimumWidth
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 1)
        val h: Int = resolveSizeAndState(
            MeasureSpec.getSize(w),
            heightMeasureSpec,
            0
        )
        widthSize = w
        heightSize = h
        setMeasuredDimension(w, h)
    }

    /// *****  Draw Download Button  **** //////
    private fun drawDownloadButton(canvas: Canvas) {

        // Rectangle
        canvas.drawRect(clipRectLeft, clipRectTop, clipRectRight, clipRectBottom, paintRectangle)
        // Circle
        canvas.drawArc(mArcBounds, starAngle, endAngle, true, paintCircle)
        // Text
        canvas.drawText(buttonText, 500F, 150F, paintText)


    }
}