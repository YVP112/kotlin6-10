package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

// Какое задание показывать на эмуляторе/телефоне:
// 6 — круги, 7 — контакты (Column), 71 — список контактов, 72 — контакт в Row,
// 8 — трекер воды, 10 — корзина
private const val SCREEN = 8

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            when (SCREEN) {
                6 -> Column(Modifier.fillMaxSize()) { Task6First(); Task6Second() }
                7 -> ContactColumn()
                71 -> ContactList()
                72 -> ContactRow()
                8 -> WaterTracker()
                10 -> ShoppingCartScreen()
            }
        }
    }
}
