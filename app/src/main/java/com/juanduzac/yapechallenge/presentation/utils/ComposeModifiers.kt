package com.juanduzac.yapechallenge.presentation.utils

import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

private var lastTapTime = 0L
private const val MULTI_CLICK_WAIT_TIME = 500L

fun Modifier.singleClickable(onClick: () -> Unit): Modifier = clickable {
    if (SystemClock.elapsedRealtime() - lastTapTime > MULTI_CLICK_WAIT_TIME) {
        lastTapTime = SystemClock.elapsedRealtime()
        onClick()
    }
}