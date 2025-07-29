package com.kiteui.jetsnackdemo

import FilterWithImage
import categories
import com.lightningkite.kiteui.Routable
import com.lightningkite.kiteui.models.Align
import com.lightningkite.kiteui.models.Icon
import com.lightningkite.kiteui.models.ImageRemote
import com.lightningkite.kiteui.models.rem
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
import com.lightningkite.kiteui.views.card
import com.lightningkite.kiteui.views.centered
import com.lightningkite.kiteui.views.direct.TextInput
import com.lightningkite.kiteui.views.direct.button
import com.lightningkite.kiteui.views.direct.col
import com.lightningkite.kiteui.views.direct.frame
import com.lightningkite.kiteui.views.direct.h1
import com.lightningkite.kiteui.views.direct.image
import com.lightningkite.kiteui.views.direct.onClick
import com.lightningkite.kiteui.views.direct.recyclerView
import com.lightningkite.kiteui.views.direct.row
import com.lightningkite.kiteui.views.direct.scrolling
import com.lightningkite.kiteui.views.direct.separator
import com.lightningkite.kiteui.views.direct.shownWhen
import com.lightningkite.kiteui.views.direct.sizeConstraints
import com.lightningkite.kiteui.views.direct.text
import com.lightningkite.kiteui.views.direct.textInput
import com.lightningkite.kiteui.views.direct.toggleButton
import com.lightningkite.kiteui.views.expanding
import com.lightningkite.kiteui.views.fieldTheme
import com.lightningkite.kiteui.views.important
import com.lightningkite.kiteui.views.l2.children
import com.lightningkite.kiteui.views.l2.dialog
import com.lightningkite.kiteui.views.l2.icon
import com.lightningkite.readable.AppState
import com.lightningkite.readable.Property
import com.lightningkite.readable.lens
import com.lightningkite.readable.reactive
import com.lightningkite.readable.shared
import kotlinx.coroutines.isActive
import lifeStyleFilters
import kotlin.collections.List

@Routable("/search")
class SearchPage: Page {
    override fun ViewWriter.render(): ViewModifiable {
        val search = Property("")
        val selectedLifeStyleFilters = Property<List<FilterWithImage>>(emptyList<FilterWithImage>())
        val searchResults = shared {
            snacks.filter {
                it.name.contains(search().lowercase())
            }
        }
        return col {
            row {
                expanding - fieldTheme -textInput {
                    content bind search
                    hint = "Search"
                    align = Align.Center
                }
            }

            separator {  }
         frame {
                shownWhen { search().isNotEmpty() } - expanding - col {
                    row {
                        lifeStyleFilters.forEach { filter ->
                            toggleButton {
                                centered - text(filter.name)
                                checked bind selectedLifeStyleFilters.lens(get = { it.contains(filter) }, set = {
                                    selectedLifeStyleFilters.value.plus(filter)
                                })
                            }
                        }
                    }
                    text {
                        ::content { "${searchResults().size} items" }
                    }
                    sizeConstraints(height = 20.rem)  - recyclerView {
                            children(searchResults, { it.id }) {
                                sizeConstraints(10.rem)  - card - row {
                                    centered - text {
                                        ::content { it().name }
                                    }
                                    image {
                                        ::source{ ImageRemote(it().imageUrl) }
                                    }
                                }
                            }
                    }
                }

                shownWhen { search().isEmpty() } - scrolling - col {
                    h1("Categories")
                    val chunkedCategories = categories.chunked(2)
                    chunkedCategories.forEach { row ->
                        row {
                            categories.forEach {
                                row {
                                    centered - text(it.name)
                                    sizeConstraints(width = 40.rem) - image {
                                        source = ImageRemote(it.imgUrl)
                                    }
                                }
                            }
                        }
                    }
                    h1("Lifestyles")
                    val chunkedLifestyles = lifeStyleFilters.chunked(2)
                    chunkedLifestyles.forEach { row ->
                        row {
                            lifeStyleFilters.forEach {
                                row {
                                    centered - text(it.name)
                                    image {
                                        source = ImageRemote(it.imgUrl)
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

    }
}