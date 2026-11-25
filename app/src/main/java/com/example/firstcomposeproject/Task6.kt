package com.example.firstcomposeproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Задание 6, вариант 1: круг исходного размера в правом верхнем углу, чёрный фон 240x120
@Composable
fun Task6First() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "Оранжевый круг",
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

// Задание 6, вариант 2: синий фон, круг по центру, растянут, фиолетовый (colorFilter)
@Composable
fun Task6Second() {
    Box(
        modifier = Modifier
            .size(width = 240.dp, height = 120.dp)
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "Фиолетовый круг",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF800080))
        )
    }
}

@Preview(name = "Задание 6 - Вариант 1", showBackground = true, showSystemUi = true, widthDp = 360, heightDp = 640)
@Composable
fun Task6Preview1() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Task6First() }
}

@Preview(name = "Задание 6 - Вариант 2", showBackground = true, showSystemUi = true, widthDp = 360, heightDp = 640)
@Composable
fun Task6Preview2() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Task6Second() }
}
