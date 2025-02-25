package com.campusdual_grupo3.bookandgo.utils.animations

import android.animation.ValueAnimator
import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

object Animations {
    fun dpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    fun animateWidthChange(view: View, fromDp: Int, toDp: Int, duration: Long = 300) {
        val context = view.context
        val fromPx = dpToPx(context, fromDp)
        val toPx = dpToPx(context, toDp)

        val animator = ValueAnimator.ofInt(fromPx, toPx)
        animator.duration = duration
        animator.interpolator = AccelerateDecelerateInterpolator() // Optional: Add an interpolator for smoother animation

        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            val layoutParams = view.layoutParams

            // Update only the width
            layoutParams.width = animatedValue
            view.layoutParams = layoutParams
        }

        animator.start()
    }
}