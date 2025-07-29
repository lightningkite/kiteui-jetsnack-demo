//package com.lightningkite.jetsnackdemo.model
//
///**
// * A fake repo for searching.
// */
//object SearchRepo {
//    fun getCategories(): List<SearchCategoryCollection> = searchCategoryCollections
//    fun getSuggestions(): List<SearchSuggestionGroup> = searchSuggestions
//
//    suspend fun search(query: String): List<Snack> = withContext(Dispatchers.Default) {
//        delay(200L) // simulate an I/O delay
//        snacks.filter { it.name.contains(query, ignoreCase = true) }
//    }
//}
//
//@Immutable
//data class SearchCategoryCollection(
//    val id: Long,
//    val name: String,
//    val categories: List<SearchCategory>
//)
//
//@Immutable
//data class SearchCategory(
//    val name: String,
//    val imageUrl: String
//)
//
//@Immutable
//data class SearchSuggestionGroup(
//    val id: Long,
//    val name: String,
//    val suggestions: List<String>
//)
//
///**
// * Static data
// */
//
//private val searchCategoryCollections = listOf(
//    SearchCategoryCollection(
//        id = 0L,
//        name = "Categories",
//        categories = listOf(
//            SearchCategory(
//                name = "Chips & crackers",
//                imageUrl = "files/chips.jpg"
//            ),
//            SearchCategory(
//                name = "Fruit snacks",
//                imageUrl = "files/fruit.jpg"
//            ),
//            SearchCategory(
//                name = "Desserts",
//                imageUrl = "files/desserts.jpg"
//            ),
//            SearchCategory(
//                name = "Nuts ",
//                imageUrl = "files/nuts.jpg"
//            )
//        )
//    ),
//    SearchCategoryCollection(
//        id = 1L,
//        name = "Lifestyles",
//        categories = listOf(
//            SearchCategory(
//                name = "Organic",
//                imageUrl = "files/organic.jpg"
//            ),
//            SearchCategory(
//                name = "Gluten Free",
//                imageUrl = "files/gluten_free.jpg"
//            ),
//            SearchCategory(
//                name = "Paleo",
//                imageUrl = "files/paleo.jpg"
//            ),
//            SearchCategory(
//                name = "Vegan",
//                imageUrl = "files/vegan.jpg"
//            ),
//            SearchCategory(
//                name = "Vegitarian",
//                imageUrl = "files/grapes.jpg"
//            ),
//            SearchCategory(
//                name = "Whole30",
//                imageUrl = "files/popcorn.jpg"
//            )
//        )
//    )
//)
//
//private val searchSuggestions = listOf(
//    SearchSuggestionGroup(
//        id = 0L,
//        name = "Recent searches",
//        suggestions = listOf(
//            "Cheese",
//            "Apple Sauce"
//        )
//    ),
//    SearchSuggestionGroup(
//        id = 1L,
//        name = "Popular searches",
//        suggestions = listOf(
//            "Organic",
//            "Gluten Free",
//            "Paleo",
//            "Vegan",
//            "Vegitarian",
//            "Whole30"
//        )
//    )
//)