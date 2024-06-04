package com.example.smart_job_finder_v2.ui.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smart_job_finder_v2.R

@Composable
fun FadingImage() {
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2500,
                easing = FastOutSlowInEasing
            )
        )
    }


    Image(
        painter = painterResource(id = R.drawable.sjf_logo),
        contentDescription = "smart job finder logo",
        modifier = Modifier
            .size(450.dp)
            .alpha(alpha.value)
    )
}