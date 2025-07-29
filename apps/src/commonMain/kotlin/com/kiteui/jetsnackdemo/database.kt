package com.kiteui.jetsnackdemo

import Snack
import SnackCollection

// simulated database



val snacks = listOf(
    Snack(
        id = 1L,
        name = "Cupcake",

        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/cupcake.jpg",
        price = 299
    ),
    Snack(
        id = 2L,
        name = "Donut",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/donut.jpg",
        price = 290
    ),
    Snack(
        id = 3L,
        name = "Eclair",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/eclair.jpg",
        price = 289
    ),
    Snack(
        id = 4L,
        name = "Froyo",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/froyo.jpg",
        price = 288
    ),
    Snack(
        id = 5L,
        name = "Gingerbread",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/gingerbread.jpg",
        price = 499
    ),
    Snack(
        id = 6L,
        name = "Honeycomb",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/honeycomb.jpg",
        price = 309
    ),
    Snack(
        id = 7L,
        name = "Ice Cream Sandwich",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/ice_cream_sandwich.jpg",
        price = 1299
    ),
    Snack(
        id = 8L,
        name = "Jellybean",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/jelly_bean.jpg",
        price = 109
    ),
    Snack(
        id = 9L,
        name = "KitKat",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/kitkat.jpg",
        price = 549
    ),
    Snack(
        id = 10L,
        name = "Lollipop",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/lollipop.jpg",
        price = 209
    ),
    Snack(
        id = 11L,
        name = "Marshmallow",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/marshmallow.jpg",
        price = 219
    ),
    Snack(
        id = 12L,
        name = "Nougat",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/nougat.jpg",
        price = 309
    ),
    Snack(
        id = 13L,
        name = "Oreo",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/oreo.jpg",
        price = 339
    ),
    Snack(
        id = 14L,
        name = "Pie",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/pie.jpg",
        price = 249
    ),
    Snack(
        id = 15L,
        name = "Chips",
        imageUrl = "https://zal.im/wasm/jetsnack/images/chips.jpg",
        price = 277
    ),
    Snack(
        id = 16L,
        name = "Pretzels",
        imageUrl = "https://zal.im/wasm/jetsnack/images/pretzels.jpg",
        price = 154
    ),
    Snack(
        id = 17L,
        name = "Smoothies",
        imageUrl = "https://zal.im/wasm/jetsnack/images/smoothies.jpg",
        price = 257
    ),
    Snack(
        id = 18L,
        name = "Popcorn",
        imageUrl = "https://zal.im/wasm/jetsnack/images/popcorn.jpg",
        price = 167
    ),
    Snack(
        id = 19L,
        name = "Almonds",
        imageUrl = "https://zal.im/wasm/jetsnack/images/almonds.jpg",
        price = 123
    ),
    Snack(
        id = 20L,
        name = "Cheese",
        imageUrl = "https://zal.im/wasm/jetsnack/images/cheese.jpg",
        price = 231
    ),
    Snack(
        id = 21L,
        name = "Apples",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/apples.jpg",
        price = 221
    ),
    Snack(
        id = 22L,
        name = "Apple sauce",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/apple_sauce.jpg",
        price = 222
    ),
    Snack(
        id = 23L,
        name = "Apple chips",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/apple_chips.jpg",
        price = 231
    ),
    Snack(
        id = 24L,
        name = "Apple juice",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/apple_juice.jpg",
        price = 241
    ),
    Snack(
        id = 25L,
        name = "Apple pie",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/apple_pie.jpg",
        price = 225
    ),
    Snack(
        id = 26L,
        name = "Grapes",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/grapes.jpg",
        price = 266
    ),
    Snack(
        id = 27L,
        name = "Kiwi",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/kiwi.jpg",
        price = 127
    ),
    Snack(
        id = 28L,
        name = "Mango",
        tagline = "A tag line",
        imageUrl = "https://zal.im/wasm/jetsnack/images/mango.jpg",
        price = 128
    )
)

fun Snack.getPriceString(): String {
    return "$${price / 100.0}"
}

val collections = listOf(
    SnackCollection(
        id = 1L,
        name = "Android's picks",
        type = CollectionType.Highlight,
        snacks = snacks.subList(0, 13).map {it.id}
    ),
    SnackCollection(
        id = 2L,
        name = "Popular on Jetsnack",
        snacks = snacks.subList(14, 19).map{it.id}
    ),
    SnackCollection(
        id = 3L,
        name = "WFH favourites" ,
        type = CollectionType.Highlight,
        snacks = snacks.subList(0, 13).map {it.id}
    ),
    SnackCollection(
        id = 4L,
        name = "Newly Added",
        snacks = snacks.subList(14, 19).map{it.id}
    ),
    SnackCollection(
        id = 5L,
        name = "Only on Jetsnack",
        type = CollectionType.Highlight,
        snacks = snacks.subList(0, 13).map {it.id}
    )

)