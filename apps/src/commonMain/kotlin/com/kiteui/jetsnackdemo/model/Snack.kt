//package com.lightningkite.jetsnackdemo.model
//
//
//@Immutable
data class Snack(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val price: Long,
    val tagline: String = "",
    val tags: Set<String> = emptySet()
)

fun Snack.getPriceString(): String {
    return "$${price / 100.0}"
}

//
///**
// * Static data
// */
//
