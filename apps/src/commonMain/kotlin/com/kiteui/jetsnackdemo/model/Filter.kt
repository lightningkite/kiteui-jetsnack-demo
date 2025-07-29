//package com.lightningkite.jetsnackdemo.model
//
//@Stable

val lifeStyleFilters = listOf(
    FilterWithImage("Organic", "https://zal.im/wasm/jetsnack/images/organic.jpg"),
    FilterWithImage("Gluten-free", "https://zal.im/wasm/jetsnack/images/gluten-free.jpg"),
    FilterWithImage("Dairy-free", "https://zal.im/wasm/jetsnack/images/dairy-free.jpg"),
    FilterWithImage("Sweet", "https://zal.im/wasm/jetsnack/images/sweet.jpg"),
    FilterWithImage("Savory", "https://zal.im/wasm/jetsnack/images/savory.jpg")
)
val categories = listOf(
    FilterWithImage(
        name = "Chips & crackers",
        imgUrl = "https://zal.im/wasm/jetsnack/images/chips.jpg"
    ),
    FilterWithImage(
        name = "Fruit snacks",
        imgUrl = "https://zal.im/wasm/jetsnack/images/fruit.jpg"
    ),
    FilterWithImage(
        name = "Desserts",
        imgUrl = "https://zal.im/wasm/jetsnack/images/dessert.jpg"
    ),
    FilterWithImage(
        name = "Nuts",
        imgUrl = "https://zal.im/wasm/jetsnack/images/nuts.jpg"
    )
)

data class FilterWithImage(
    val name:String,
    val imgUrl: String
)
//val priceFilters = listOf(
//    Filter(name = "$"),
//    Filter(name = "$$"),
//    Filter(name = "$$$"),
//    Filter(name = "$$$$")
//)
//val sortFilters = listOf(
//    Filter(name = "Android's favorite (default)", icon = Icons.Filled.Android),
//    Filter(name = "Rating", icon = Icons.Filled.Star),
//    Filter(name = "Alphabetical", icon = Icons.Filled.SortByAlpha)
//)
//
//val categoryFilters = listOf(
//    Filter(name = "Chips & crackers"),
//    Filter(name = "Fruit snacks"),
//    Filter(name = "Desserts"),
//    Filter(name = "Nuts")
//)
//val lifeStyleFilters = listOf(
//    Filter(name = "Organic"),
//    Filter(name = "Gluten-free"),
//    Filter(name = "Dairy-free"),
//    Filter(name = "Sweet"),
//    Filter(name = "Savory")
//)
//
//var sortDefault = sortFilters.get(0).name