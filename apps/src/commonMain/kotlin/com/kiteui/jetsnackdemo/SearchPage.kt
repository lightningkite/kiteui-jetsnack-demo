package com.kiteui.jetsnackdemo

import FilterWithImage
import categories
import com.lightningkite.kiteui.Routable
import com.lightningkite.kiteui.models.Align
import com.lightningkite.kiteui.models.Color
import com.lightningkite.kiteui.models.Edges
import com.lightningkite.kiteui.models.ImageRemote
import com.lightningkite.kiteui.models.ImageScaleType
import com.lightningkite.kiteui.models.ScreenTransition
import com.lightningkite.kiteui.models.rem
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
import com.lightningkite.kiteui.views.bold
import com.lightningkite.kiteui.views.centered
import com.lightningkite.kiteui.views.direct.col
import com.lightningkite.kiteui.views.direct.frame
import com.lightningkite.kiteui.views.direct.h2
import com.lightningkite.kiteui.views.direct.image
import com.lightningkite.kiteui.views.direct.recyclerView
import com.lightningkite.kiteui.views.direct.row
import com.lightningkite.kiteui.views.direct.scrolling
import com.lightningkite.kiteui.views.direct.sizeConstraints
import com.lightningkite.kiteui.views.direct.swapView
import com.lightningkite.kiteui.views.direct.swapping
import com.lightningkite.kiteui.views.direct.text
import com.lightningkite.kiteui.views.direct.textInput
import com.lightningkite.kiteui.views.direct.toggleButton
import com.lightningkite.kiteui.views.direct.unpadded
import com.lightningkite.kiteui.views.expanding
import com.lightningkite.kiteui.views.fieldTheme
import com.lightningkite.kiteui.views.l2.RecyclerViewPlacerVerticalGrid
import com.lightningkite.kiteui.views.l2.children
import com.lightningkite.readable.Property
import com.lightningkite.readable.lens
import com.lightningkite.readable.shared
import lifeStyleFilters
import kotlin.collections.List
import kotlin.collections.plus

@Routable("/search")
class SearchPage: Page {
    override fun ViewWriter.render(): ViewModifiable {
        val search = Property("")

        return col {
            row {

                expanding - fieldTheme -textInput {
                    content bind search
                    hint = "Search"
                    align = Align.Center
                }
            }
            expanding - swapView {
                swapping(
                    transition = {
                        ScreenTransition.PullUp
                    },
                    current = {search().isNotEmpty() },
                    views = {
                        when(it) {
                            true -> searchResultsView(search)
                                false -> categoriesSearch()
                        }
                    }
                )
            }
        }
    }
}

fun ViewWriter.categoriesSearch() : ViewModifiable {
    return                 scrolling - col {
        h2("Categories")
        val chunkedCategories = categories.chunked(2)
        chunkedCategories.forEach { row ->
             row {
                row.forEach {
                    expanding -GradientBackgroundSemantic(lightBlue,darkBlue).onNext - frame {
                        paddingByEdge = Edges(1.rem, 0.rem,0.rem,0.rem)
                       expanding - row {

                            expanding -centered -bold - text(it.name)
                           TestCircleIconSemantic(10.rem).onNext - sizeConstraints(height = 20.rem, width = 13.rem) - image {
                               description = it.name
                                source = ImageRemote(it.imgUrl)
                               scaleType = ImageScaleType.Crop
                           }
                        }
                    }
                }
            }
        }
        h2("Lifestyles")
        val chunkedLifestyles = lifeStyleFilters.chunked(2)
        chunkedLifestyles.forEach { row ->
            row {
                row.forEach {
                    expanding -GradientBackgroundSemantic(Color.fromHexString("#b17fe2"),Color.fromHexString("#d47795")).onNext - frame {
                        paddingByEdge = Edges(1.rem, 0.rem,0.rem,0.rem)
                        expanding - row {

                            expanding -centered -bold - text(it.name)
                            TestCircleIconSemantic(10.rem).onNext - sizeConstraints(height = 20.rem, width = 13.rem) - image {
                                ::description {
                                    it.name
                                }
                                description = it.name
                                source = ImageRemote(it.imgUrl)
                                scaleType = ImageScaleType.Crop
                            }
                        }
                    }
                }
            }
        }
    }
}


fun ViewWriter.searchResultsView(search: Property<String>) : ViewModifiable{
    val selectedLifeStyleFilters = Property<List<FilterWithImage>>(emptyList<FilterWithImage>())
    val searchResults = shared {
        snacks.filter {
            it.name.contains(search().lowercase())
        }
    }
    return scrolling - col {
        row {
            lifeStyleFilters.forEach { filter ->
                FilterCard.onNext -  col {
                    padding = 0.0.rem
                    gap = 0.0.rem
                    toggleButton {
                        padding = 0.5.rem
                        gap = 0.5.rem
                        unpadded -centered - text{
                            content =filter.name}
                        checked bind selectedLifeStyleFilters.lens(get = { it.contains(filter) }, set = {
                            selectedLifeStyleFilters.value.plus(filter)
                        })
                    }
                }
            }
        }
        text {
            ::content { "${searchResults().size} items" }
        }
        expanding - recyclerView {
            placer = RecyclerViewPlacerVerticalGrid(4,1.0)
            children(searchResults, { it.id }) {
                col {
                    centered -  snackCardGradientSquareBackground(it,darkBlue,lightBlue)
                }
            }
        }
    }
}