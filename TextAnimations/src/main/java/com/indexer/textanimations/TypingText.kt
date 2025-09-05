package com.indexer.textanimations

/*
 * A Jetpack Compose Composable that displays text with a typing animation effect.
 *
 * Features:
 * - Simulates text being typed character by character at a configurable speed.
 * - Optionally shows a blinking cursor (like in a terminal or chat app).
 * - Can be reused in intros, chat messages, storytelling UIs, or any animated text display.
 *
 * @param text The full text string to be animated.
 * @param typingSpeed Delay in milliseconds for each character to appear (default: 100ms).
 * @param withCursor Whether to display a blinking cursor at the end of the text (default: false).
 *
 * Example:
 * ```
 * TypingText(
 *     text = "Hello, World!",
 *     typingSpeed = 80L,
 *     withCursor = true
 * )
 * ```
 */

import androidx.compose.runtime.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kotlinx.coroutines.delay

@Composable
fun TypingText(
    text: String,
    typingSpeed: Long = 100L,
    withCursor: Boolean = false
) {
    var visibleText by remember { mutableStateOf("") }
    var showCursor by remember { mutableStateOf(true) }

    LaunchedEffect(text) {
        visibleText = ""
        text.forEachIndexed { index, _ ->
            visibleText = text.take(index + 1)
            delay(typingSpeed)
        }
    }

    if (withCursor) {
        LaunchedEffect(Unit) {
            while (true) {
                showCursor = !showCursor
                delay(500L)
            }
        }
    }

    Text(
        text = visibleText + if (withCursor && showCursor) "|" else "",
        style = MaterialTheme.typography.bodyLarge
    )
}
