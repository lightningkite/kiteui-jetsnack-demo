//package com.lightningkite.jetsnackdemo.model
//
//
//@Immutable
data class SnackCollection(
    val id: Long,
    val name: String,
    val snacks: List<Long>,
    val type: CollectionType = CollectionType.Normal
)
//
enum class CollectionType { Normal, Highlight }
//
///**
// * A fake repo
// */
//object SnackRepo {
//    fun getSnacks(): List<SnackCollection> = snackCollections
//    fun getSnack(snackId: Long) = snacks.find { it.id == snackId }!!
//    fun getRelated(@Suppress("UNUSED_PARAMETER") snackId: Long) = related
//    fun getInspiredByCart() = inspiredByCart
////    fun getFilters() = filters
////    fun getPriceFilters() = priceFilters
//    fun getCart() = cart
////    fun getSortFilters() = sortFilters
////    fun getCategoryFilters() = categoryFilters
////    fun getSortDefault() = sortDefault
//    fun getLifeStyleFilters() = lifeStyleFilters
//}
//
///**
// * Static data
// */
//
//val tastyTreats = SnackCollection(
//    id = 1L,
//    name = "Android's picks",
//    type = CollectionType.Highlight,
//    snacks = snacks.subList(0, 13)
//)
//
//val popular = SnackCollection(
//    id = 2L,
//    name = "Popular on Jetsnack",
//    snacks = snacks.subList(14, 19)
//)
//
//val wfhFavs = tastyTreats.copy(
//    id = 3L,
//    name = "WFH favourites"
//)
//
//val newlyAdded = popular.copy(
//    id = 4L,
//    name = "Newly Added"
//)
//
//val exclusive = tastyTreats.copy(
//    id = 5L,
//    name = "Only on Jetsnack"
//)
//
//private val also = tastyTreats.copy(
//    id = 6L,
//    name = "Customers also bought"
//)
//
//private val inspiredByCart = tastyTreats.copy(
//    id = 7L,
//    name = "Inspired by your cart"
//)

//private val snackCollections = listOf(
//    tastyTreats,
//    popular,
//    wfhFavs,
//    newlyAdded,
//    exclusive
//)

//private val related = listOf(
//    also,
//    popular
//)

//private val cart = listOf(
//    OrderLine(snacks[4], 2),
//    OrderLine(snacks[6], 3),
//    OrderLine(snacks[8], 1)
//)

//@Immutable
data class OrderLine(
    val snack: Snack,
    val count: Int
)