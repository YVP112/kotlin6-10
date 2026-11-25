package com.example.firstcomposeproject

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

data class Cart(
    val products: List<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

/*
 * Что было исправлено:
 * 1. totalSum / productSize были полями Activity и менялись прямо в теле composable-функции
 *    (побочный эффект при каждой рекомпозиции) -> сумма росла при каждой перерисовке.
 *    Теперь сумма вычисляется из списка: products.sumOf { it.price }.
 * 2. Toast вызывался прямо в composable -> показывался при каждой рекомпозиции.
 *    Теперь он вызывается один раз в обработчике нажатия «Добавить товар».
 * 3. Кнопка «Удалить товар» зависела от productSize, который не обновлялся нормально.
 *    Теперь она показывается по условию products.isNotEmpty().
 */
@Composable
fun ShoppingCartScreen() {
    val context = LocalContext.current
    var products by remember {
        mutableStateOf(
            listOf(
                Product(0, "Товар #1", 100),
                Product(1, "Товар #2", 150),
                Product(2, "Товар #3", 56)
            )
        )
    }
    val totalSum = products.sumOf { it.price }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Корзина товаров", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(8.dp))

        for (product in products) {
            Text(
                text = product.name + " - " + product.price + " рублей",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Товаров на сумму: $totalSum рублей", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))

        AddProductSection {
            val newProduct = Product(
                id = products.size,
                name = "Товар #${products.size + 1}",
                price = Random.nextInt(0, 100)
            )
            products = products + newProduct
            // Если сумма заказа больше 500, показываем сообщение о бесплатной доставке
            if (products.sumOf { it.price } > 500) {
                Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
            }
        }

        RemoveProductSection(hasProducts = products.isNotEmpty()) {
            products = products.dropLast(1)
        }
    }
}

// Добавление товара
@Composable
fun AddProductSection(
    onAdd: () -> Unit
) {
    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

// Удаление товара
@Composable
fun RemoveProductSection(
    hasProducts: Boolean,
    onRemove: () -> Unit
) {
    if (hasProducts) {
        Button(onClick = onRemove) {
            Text(text = "Удалить товар")
        }
    }
}

@Preview(name = "Задание 10", showBackground = true, showSystemUi = true)
@Composable
fun Task10Preview() {
    ShoppingCartScreen()
}
