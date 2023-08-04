package com.jhlee.quiz_libs.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.jhlee.quiz_libs.R

class StarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var maxStar: Int = 0
    private var ratingStar: Int = 0
    private var width: Float = 0f

    private var startViewList: ArrayList<ImageView> = arrayListOf()

    fun setWidth(width: Float) {
        this.width = width
    }

    fun setMaxStar(maxStar: Int) {
        this.maxStar = maxStar

    }

    fun setRatingStar(ratingStar: Int) {
        this.ratingStar = ratingStar
        refreshStar()
    }

    init {
        // XML 레이아웃에서 정의한 속성을 가져오기 위해 TypedArray를 사용합니다.
        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.StarView, defStyleAttr, 0)

        try {
            // TypedArray에서 가져온 속성 값을 변수에 저장합니다.
            maxStar = typedArray.getInt(R.styleable.StarView_max, 5)
            ratingStar = typedArray.getInt(R.styleable.StarView_rating, 0)
            width = typedArray.getDimension(R.styleable.StarView_star_size, 10f)
        } finally {
            // TypedArray 사용이 끝나면 반드시 recycle() 메서드를 호출하여 리소스를 해제해야 합니다.
            typedArray.recycle()
        }

        orientation = HORIZONTAL
        for (i in 1..maxStar) {
            val imageView = AppCompatImageView(context)
            val drawable = if (i < ratingStar) {
                context.getDrawable(R.drawable.btn_menu_popular_n)
            } else {
                context.getDrawable(R.drawable.btn_menu_popular_s)
            }
            imageView.setImageDrawable(drawable)
            imageView.layoutParams = LayoutParams(width.toInt(), width.toInt())
            startViewList.add(imageView)
            addView(imageView)
        }
    }

    private fun refreshStar() {
        var i = 0;
        startViewList.forEach {
            if (i < ratingStar) {
                it.setImageDrawable(context.getDrawable(R.drawable.btn_menu_popular_s))
            } else {
                it.setImageDrawable(context.getDrawable(R.drawable.btn_menu_popular_n))
            }
            i++
        }
    }
}