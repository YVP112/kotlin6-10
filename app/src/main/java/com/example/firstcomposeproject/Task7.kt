package com.example.firstcomposeproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Contact(
    val name: String,
    val patronymic: String,
    val surname: String,
    val phone: String?,
    val address: String
)

private val contacts = listOf(
    Contact("Евгений", "Андреевич", "Лукашин", "+7 495 495 95 95", "г. Москва, 3-я улица Строителей, д. 25, кв. 12"),
    Contact("Василий", "Егорович", "Кузякин", null, "Ивановская область, дер. Крутово, д. 4"),
    Contact("Людмила", "Прокофьевна", "Калугина", "+7 495 788 78 78", "Москва, Большая Никитская, д. 43, кв. 290")
)

/* ---------- 1. Простой Column (текст слева, без фона) ---------- */
@Composable
fun ContactColumn(contact: Contact = contacts[0]) {
    Column {
        Text("Имя: ${contact.name}")
        Text("Отчество: ${contact.patronymic}")
        Text("Фамилия: ${contact.surname}")
        Text("Мобильный телефон: ${contact.phone ?: "---"}")
        Text("Адрес: ${contact.address}")
    }
}

/* ---------- 2. Карточка контакта: серый фон, текст по центру ---------- */
@Composable
fun ContactCard(contact: Contact, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Имя: ${contact.name}")
        Text("Отчество: ${contact.patronymic}")
        Text("Фамилия: ${contact.surname}")
        Text("Мобильный телефон: ${contact.phone ?: "---"}")
        Text("Адрес: ${contact.address}")
    }
}

/* ---------- 2. Список контактов в Column ---------- */
@Composable
fun ContactList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        contacts.forEach { ContactCard(it) }
    }
}

/* ---------- 3. Row: данные контакта + картинка (звезда) справа ---------- */
@Composable
fun ContactRow(contact: Contact = contacts[0]) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Имя: ${contact.name}", textAlign = TextAlign.Center)
            Text("Отчество: ${contact.patronymic}", textAlign = TextAlign.Center)
            Text("Фамилия: ${contact.surname}", textAlign = TextAlign.Center)
            Text("Мобильный телефон: ${contact.phone ?: "---"}", textAlign = TextAlign.Center)
            Text("Адрес: ${contact.address}")
        }
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Избранное",
            tint = Color(0xFFFF9800)
        )
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactColumnPreview() {
    ContactColumn()
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ListPreview() {
    ContactList()
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun ContactRowPreview() {
    ContactRow()
}
