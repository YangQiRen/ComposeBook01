package com.example.composebook01.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope

class MyFlingBehavior : FlingBehavior {
    private val scale = Animatable(1f) // 使用 Animatable 定义缩放比例
    private val scaleAnimationSpec: AnimationSpec<Float> = TweenSpec(500) // 缩放动画的规格

    override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
        // 根据滑动速度计算缩放比例
        val newScale = if (initialVelocity > 0) {
            1.5f // 向下滑动时放大
        } else {
            0.5f // 向上滑动时缩小
        }

        // 更新缩放比例，并播放缩放动画
        scale.snapTo(newScale)
        scale.animateTo(newScale, animationSpec = scaleAnimationSpec)

        // 处理滑动行为逻辑
        // ...
        return 0f
    }
}
