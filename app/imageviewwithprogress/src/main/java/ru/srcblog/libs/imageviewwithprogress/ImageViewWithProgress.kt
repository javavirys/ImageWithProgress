package ru.srcblog.libs.imageviewwithprogress

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar

class ImageViewWithProgress @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var showProgress: Boolean = false;

    private val imageView: ImageView;

    private val progressBar: ProgressBar;

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ImageViewWithProgress,
            defStyleAttr,
            defStyleRes
        )

        showProgress = typedArray.getBoolean(R.styleable.ImageViewWithProgress_showProgress, false)

        imageView = ImageView(context);

        progressBar = ProgressBar(context);

        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        progressBar.parent?.let { (it as ViewGroup).removeView(progressBar) }
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        addView(progressBar, layoutParams)
        imageView.parent?.let { (it as ViewGroup).removeView(imageView) }
        val layoutParamsForImage = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(imageView, layoutParamsForImage)
    }

}