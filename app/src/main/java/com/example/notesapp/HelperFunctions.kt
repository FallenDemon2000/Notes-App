package com.example.notesapp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastelGradientTrack(
    state: SliderState,
) {
    Canvas(
        Modifier
            .fillMaxWidth()
            // .height(40.dp)
            .height(20.dp),
    ) {
        val trackHeight = size.height
        val fullWidth = size.width

        // Compute active width using the *new* API
        val fraction = state.value / state.valueRange.endInclusive

        // Inactive background track
        drawRoundRect(
            // color = Color.White.copy(alpha = 0.4f),
            brush = pastelGradient,
            size = Size(fullWidth, trackHeight),
            cornerRadius = CornerRadius(trackHeight / 3),
        )

        // Active gradient track
        drawRoundRect(
            brush = pastelGradient,
            size = Size(fullWidth * fraction, trackHeight),
            cornerRadius = CornerRadius(trackHeight / 3),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastelThumb(state: SliderState) {
    val fraction = (state.value - state.valueRange.start) /
        (state.valueRange.endInclusive - state.valueRange.start)

    val fillColor = pastelColorAt(fraction)
    val borderColor = MaterialTheme.colorScheme.surfaceContainerLow
    // val borderColor = MaterialTheme.colorScheme.primary

    Canvas(
        Modifier
            .size(width = 22.dp, height = 44.dp), // your thick thumb size
    ) {
        val radius = CornerRadius(size.height / 4f)

        // Filled rounded rect (the thumb body)
        drawRoundRect(
            color = fillColor,
            cornerRadius = radius,
        )

        // Border
        drawRoundRect(
            // color = Color.White.copy(alpha = 0.9f), // border color
            color = borderColor,
            cornerRadius = radius,
            style = Stroke(
                width = 12f,
            ),
        )
    }
}

fun pastelColorAt(fraction: Float): Color {
    val count = pastelStops.size - 1
    val index = (fraction * count).coerceIn(0f, count.toFloat() - .01f)
    val i = index.toInt()
    val localFraction = index - i
    return lerpColor(pastelStops[i], pastelStops[i + 1], localFraction)
}

fun randomBGColor(): Color {
    return pastelColor(
        Random.nextFloat(),
        Random.nextFloat(),
        Random.nextFloat(),
        1f,
    )
}

private val pastelStops = listOf(
    pastelColor(0f, 0f, 1f),
    pastelColor(0f, 1f, 1f),
    pastelColor(0f, 1f, 0f),
    pastelColor(1f, 1f, 0f),
    pastelColor(1f, 0f, 0f),
    pastelColor(1f, 0f, 1f),
)

private val pastelGradient =
    Brush.horizontalGradient(colors = pastelStops)

private fun pastelColor(
    red: Float,
    green: Float,
    blue: Float,
    alpha: Float = 1f,
    isDarkTheme: Boolean = true,
): Color {
    // Pastel colors: move values toward high brightness and low saturation
    val base = (if (isDarkTheme) 1f else 2f) / 3f
    val variance = 0.4f // how much it can vary above the base

    val r = base + red * variance
    val g = base + green * variance
    val b = base + blue * variance

    return Color(r, g, b, alpha)
}

private fun lerpColor(start: Color, end: Color, fraction: Float): Color {
    return Color(
        red = lerp(start, end, fraction).red,
        green = lerp(start, end, fraction).green,
        blue = lerp(start, end, fraction).blue,
        alpha = 1f,
    )
}
