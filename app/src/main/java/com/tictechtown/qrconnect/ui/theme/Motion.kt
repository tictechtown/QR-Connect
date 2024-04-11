package com.tictechtown.qrconnect.ui.theme

import android.view.animation.PathInterpolator
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object MotionConstants {
    public const val DefaultMotionDuration: Int = 300
    public const val DefaultFadeInDuration: Int = 150
    public const val DefaultFadeOutDuration: Int = 75
    public val DefaultSlideDistance: Dp = 30.dp
}

@Composable
public fun rememberSlideDistance(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
): Int {
    val density = LocalDensity.current
    return remember(density, slideDistance) {
        with(density) { slideDistance.roundToPx() }
    }
}


private val emphasizedPath = android.graphics.Path().apply {
    moveTo(0f, 0f)
    cubicTo(0.05f, 0f, 0.133333f, 0.06f, 0.166666f, 0.4f)
    cubicTo(0.208333f, 0.82f, 0.25f, 1f, 1f, 1f)
}

val emphasizedDecelerate = PathInterpolator(0.05f, 0.7f, 0.1f, 1f)
val emphasizedAccelerate = PathInterpolator(0.3f, 0f, 0.8f, 0.15f)
val emphasized = PathInterpolator(emphasizedPath)

val EmphasizedEasing: Easing = Easing { fraction -> emphasized.getInterpolation(fraction) }
val EmphasizedDecelerateEasing =
    Easing { fraction -> emphasizedDecelerate.getInterpolation(fraction) }
val EmphasizedAccelerateEasing =
    Easing { fraction -> emphasizedAccelerate.getInterpolation(fraction) }
