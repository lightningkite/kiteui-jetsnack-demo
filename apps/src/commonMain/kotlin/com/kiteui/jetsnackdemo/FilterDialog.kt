package com.kiteui.jetsnackdemo

import FilterWithImage
import categories
import com.kiteui.jetsnackdemo.DescriptionTextSemantic.withoutBack
import com.lightningkite.kiteui.models.Align
import com.lightningkite.kiteui.models.Color
import com.lightningkite.kiteui.models.Edges
import com.lightningkite.kiteui.models.Icon
import com.lightningkite.kiteui.models.ImageRemote
import com.lightningkite.kiteui.models.ImageScaleType
import com.lightningkite.kiteui.models.ThemeDerivation
import com.lightningkite.kiteui.models.rem
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.navigation.dialogPageNavigator
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
import com.lightningkite.kiteui.views.atTopStart
import com.lightningkite.kiteui.views.bold
import com.lightningkite.kiteui.views.centered
import com.lightningkite.kiteui.views.direct.button
import com.lightningkite.kiteui.views.direct.col
import com.lightningkite.kiteui.views.direct.frame
import com.lightningkite.kiteui.views.direct.h2
import com.lightningkite.kiteui.views.direct.image
import com.lightningkite.kiteui.views.direct.onClick
import com.lightningkite.kiteui.views.direct.row
import com.lightningkite.kiteui.views.direct.sizeConstraints
import com.lightningkite.kiteui.views.direct.slider
import com.lightningkite.kiteui.views.direct.space
import com.lightningkite.kiteui.views.direct.text
import com.lightningkite.kiteui.views.direct.toggleButton
import com.lightningkite.kiteui.views.direct.unpadded
import com.lightningkite.kiteui.views.expanding
import com.lightningkite.kiteui.views.l2.icon
import com.lightningkite.readable.Property
import com.lightningkite.readable.lens
import lifeStyleFilters
import kotlin.collections.plus

class FilterDialog : Page, UseFullPage {
    val availableFilterPrices = listOf("$", "$$", "$$$", "$$$$")
    val selectedPriceFilters = Property(emptyList<String>())

    val selectedLifeStylesFilters = Property(emptyList<FilterWithImage>())
    val selectedCategoriesFilters = Property(emptyList<FilterWithImage>())

    val maxCaloriesSelected = Property(0.0f)

    override fun ViewWriter.render(): ViewModifiable {
        return col {
            frame {
                atTopStart - button {
                    icon(Icon.close, "Close")
                    onClick {
                        dialogPageNavigator.dismiss()
                    }
                }
                bold - centered - text {
                    content = "Filters"
                    align = Align.Center
                }
            }
            unpadded - h2("Sort")
            row {
                icon(Icon.androidLogo, "Android")
                text("Android's Favorite (default)")
            }
            row {
                icon(Icon.star, "Star")
                text("Rating")
            }
            unpadded - h2("Price")
            row {
                expanding - space()
                availableFilterPrices.forEach { filter ->
                    FilterCard.onNext - col {
                        padding = 0.0.rem
                        gap = 0.0.rem
                        toggleButton {
                            paddingByEdge = Edges(1.5.rem, 0.2.rem, 1.5.rem, 0.2.rem)
                            gap = 0.5.rem
                            unpadded - centered - text {
                                content = filter
                            }
                            checked bind selectedPriceFilters.lens(get = { it.contains(filter) }, set = {
                                selectedPriceFilters.value.plus(filter)
                            })
                        }
                    }
                }
                expanding - space()
            }
            unpadded - h2("Category")
            row {
                expanding - space()
                categories.forEach { filter ->
                    FilterCard.onNext - col {
                        padding = 0.0.rem
                        gap = 0.0.rem
                        toggleButton {
                            paddingByEdge = Edges(1.5.rem, 0.2.rem, 1.5.rem, 0.2.rem)
                            gap = 0.5.rem
                            unpadded - centered - text {
                                content = filter.name
                            }
                            checked bind selectedCategoriesFilters.lens(get = { it.contains(filter) }, set = {
                                selectedCategoriesFilters.value.plus(filter)
                            })
                        }
                    }
                }
                expanding - space()
            }
            row {
                unpadded - centered - h2("Max Calories")
                centered - ThemeDerivation.invoke {
                    it.withoutBack(
                        foreground = purple
                    )
                }.onNext - text("per serving")
            }
            slider {
                value bind maxCaloriesSelected
            }
            unpadded - h2("LifeStyle")
            row {

                expanding - space()

                lifeStyleFilters.forEach { filter ->
                    FilterCard.onNext - col {
                        padding = 0.0.rem
                        gap = 0.0.rem
                        toggleButton {
                            paddingByEdge = Edges(1.5.rem, 0.2.rem, 1.5.rem, 0.2.rem)
                            gap = 0.5.rem
                            unpadded - centered - text {
                                content = filter.name
                            }
                            checked bind selectedLifeStylesFilters.lens(get = { it.contains(filter) }, set = {
                                selectedLifeStylesFilters.value.plus(filter)
                            })
                        }
                    }
                }
                expanding - space()
            }

        }
    }


}