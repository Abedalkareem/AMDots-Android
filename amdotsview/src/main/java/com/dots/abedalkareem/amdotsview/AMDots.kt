package com.dots.abedalkareem.amdotsview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.view.Gravity
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate


class AMDots : LinearLayout {

    //region Private variables
    private var defaultsColors = listOf(Color.parseColor("#3cba54"), Color.parseColor("#f4c20d"), Color.parseColor("#db3236"), Color.parseColor("#4885ed"))
    private var currentViewIndex = 0
    private var dotSize = 0
    private var timer: Timer? = null
    //endregion

    //region Public variables
    /** Space between each dot, the default is `10`. */
    var spacing = 10
    /** Animation duration for each dot it should be more than `0.1`, the default is `0.7`.  */
    var animationDuration = 500

    /** The negative time you need the animation to run before the prevuse animation finish
    (If you set it for 0.2, the next animation will run before 0.2 second before the current animation finish).
    the default value is `0.2`.  */
    var aheadTime = 250
    /** Animation type, do you want the dot to `JUMP`, `SCALE` or `SHAKE`.  */
    var animationType = AnimationType.SCALE
    /** A Boolean value that controls whether the must be hidden when the animation is stopped.  */
    var hidesWhenStopped = true

    /** The circles `color`, the number of dots will be the same as the number of colors,
    so if you have 3 colors, you will have 3 dots.
    Note: you should set the colors before add the view to super view.  */
    var colors: List<Int>? = null
    //endregion

    //region Constructors
    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }
    //endregion

    //region Setup views
    private fun init(attrs: AttributeSet?) {
        setupAttributes(attrs)
        drawCircle()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.AMDots, 0, 0)
        spacing = typedArray.getInt(R.styleable.AMDots_spacing, 10)
        animationDuration = typedArray.getInt(R.styleable.AMDots_animationDuration, 400)
        aheadTime = typedArray.getInt(R.styleable.AMDots_aheadTime, 200)
        hidesWhenStopped = typedArray.getBoolean(R.styleable.AMDots_hidesWhenStopped, true)

        val type = typedArray.getInt(R.styleable.AMDots_animationType, 0)
        animationType = AnimationType.values()[type]

        val stringColors: Array<CharSequence>? = typedArray.getTextArray(R.styleable.AMDots_colors)
        val intColors = mutableListOf<Int>()

        if (stringColors != null) {
            for (color in stringColors) {
                intColors.add(Color.parseColor(color.toString()))
            }
        }

        if (intColors.size != 0) {
            colors = intColors
        }
        colors = colors ?: defaultsColors

        weightSum = colors!!.size.toFloat()

        gravity = Gravity.CENTER_VERTICAL

    }


    private fun drawCircle() {
        colors!!.forEachIndexed { _, color ->
            val view = DotView(context, color)
            view.layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
            val layoutParams = view.layoutParams as LayoutParams
            layoutParams.setMargins(spacing, spacing, spacing, spacing)
            layoutParams.gravity = Gravity.CENTER_VERTICAL
            addView(view)
        }

        start()
    }
    //endregion

    //region Animation
    private fun startAnimation() {
        post {
            currentViewIndex += 1
            if (currentViewIndex >= childCount) {
                currentViewIndex = 0
            }
            when (animationType) {
                AnimationType.SCALE -> scaleAnimation()
                AnimationType.JUMP -> moveAnimation()
                AnimationType.SHAKE -> moveAnimation()
            }
        }
    }

    private fun scaleAnimation() {
        val view = getChildAt(currentViewIndex)
        val duration = (animationDuration / 2).toLong()
        view.animate().scaleX(1.2f).scaleY(1.2f).setDuration(duration).withEndAction {
            view.animate().setStartDelay(duration).scaleX(1f).scaleY(1f).setDuration(duration).start()
        }.start()
    }

    private fun moveAnimation() {
        val view = getChildAt(currentViewIndex)
        val duration = (animationDuration / 2).toLong()
        if (animationType == AnimationType.JUMP) {
            view.animate().translationYBy(20f).setDuration(duration).withEndAction {
                view.animate().setStartDelay(300).translationYBy(-20f).setDuration(duration).start()
            }.start()
        } else {
            view.animate().translationXBy(-10f).setDuration(duration).withEndAction {
                view.animate().translationXBy(10f).setDuration(duration).start()
            }.start()
        }
    }
    //endregion

    //region Measure
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = MeasureSpec.getSize(widthMeasureSpec)

        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, spacing.toFloat(), resources.displayMetrics)
        val width = size - ((colors!!.size - 1) * px)
        dotSize = (width / colors!!.size).toInt()

        setMeasuredDimension(size, dotSize * 2)

    }
    //endregion

    //region Public Methods (Stop/Start)
    /** Use it to start the animation for the AMDots.  */
    fun start() {

        if (colors!!.isEmpty()) {
            return
        }
        visibility = View.VISIBLE

        timer = Timer()
        timer?.scheduleAtFixedRate(0, (animationDuration - aheadTime).toLong()) {
            this@AMDots.startAnimation()
        }
    }

    /** Use it to stop the animation for the AMDots.  */
    fun stop() {
        timer?.cancel()
        timer?.purge()
        timer = null
        if (hidesWhenStopped) {
            visibility = View.INVISIBLE
        }
    }
    //endregion

}

//region AnimationType
enum class AnimationType {
    JUMP, SCALE, SHAKE
}
//endregion

//region DotView
@SuppressLint("ViewConstructor")
private class DotView(context: Context, private var color: Int) : View(context) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = color
        paint.style = Paint.Style.FILL
        val radius = width / 2f
        canvas.drawCircle(radius, radius, radius, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(size, size)
    }

}
//endregion