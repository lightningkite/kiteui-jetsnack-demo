package com.kiteui.jetsnackdemo

import FilterWithImage
import Snack
import com.kiteui.jetsnackdemo.DescriptionTextSemantic.withBack
import com.lightningkite.kiteui.Routable
import com.lightningkite.kiteui.models.Color
import com.lightningkite.kiteui.models.CornerRadii
import com.lightningkite.kiteui.models.Edges
import com.lightningkite.kiteui.models.Icon
import com.lightningkite.kiteui.models.ImageRemote
import com.lightningkite.kiteui.models.ImageScaleType
import com.lightningkite.kiteui.models.ThemeDerivation
import com.lightningkite.kiteui.models.rem
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.navigation.dialogPageNavigator
import com.lightningkite.kiteui.navigation.mainPageNavigator
import com.lightningkite.kiteui.views.*
import com.lightningkite.kiteui.views.direct.*
import com.lightningkite.kiteui.views.l2.icon
import com.lightningkite.readable.Property
import com.lightningkite.readable.Readable
import com.lightningkite.readable.lens
import com.lightningkite.readable.shared
import lifeStyleFilters
import kotlin.collections.filter


@Routable("/")
class HomePage : Page {
    override fun ViewWriter.render(): ViewModifiable {

        val selectedLifeStyleFilters = Property<List<FilterWithImage>>(emptyList<FilterWithImage>())

        val tastyTreatsCollection = collections.find { it.id == 1L }
        val popularCollection = collections.find { it.id == 2L }
        val wfhFavsCollection = collections.find { it.id == 3L }
        val newlyAdded = collections.find { it.id == 4L }
        val exclusiveCollection = collections.find { it.id == 5L }

        val tastyTreatsSnacks = shared {
            println("DEBUg tastyTreatsCollection ${tastyTreatsCollection}")
            val test = tastyTreatsCollection?.let { collection ->
                val collectionSnacks = collection.snacks
                println("DEBUG test1 ${collectionSnacks.size}")
                snacks.filter { collectionSnacks.contains(it.id) }
            } ?: emptyList()
            println("DEBUG test2 ${test.size}")
            test
        }

        val popularOnJetsnackSnacks = shared {
            popularCollection?.let {
                val collectionSnacks = it.snacks
                snacks.filter { collectionSnacks.contains(it.id) }
            } ?: emptyList()
        }


        val wfhFavsSnacks = shared {
            exclusiveCollection?.let {
                val collectionSnacks = it.snacks
                snacks.filter { collectionSnacks.contains(it.id) }
            } ?: emptyList()
        }


        val newlyAddedSnacks = shared {
            wfhFavsCollection?.let {
                val collectionSnacks = it.snacks
                snacks.filter { collectionSnacks.contains(it.id) }
            } ?: emptyList()
        }

        val exclusiveSnacks = shared {
            exclusiveCollection?.let {
                val collectionSnacks = it.snacks
                snacks.filter { collectionSnacks.contains(it.id) }
            } ?: emptyList()
        }


        return scrolling - col {
            separator()
            row {
                padding = 0.5.rem
                gap = 0.5.rem
                FilterCard.onNext - button {
                    gap = 0.5.rem
                    padding = 0.2.rem
                    icon(Icon.filter, "Filter")
                    onClick {
                        dialogPageNavigator.navigate(FilterDialog())
                    }
                }
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
                            checked bind selectedLifeStyleFilters.lens(get = { it.contains(filter) }, set = {
                                selectedLifeStyleFilters.value.plus(filter)
                            })
                        }
                    }
                }
            }
            separator()
            row {
                expanding - h2(tastyTreatsCollection?.name ?: "Snacks")
                PurpleIconColor.onNext - button {
                    icon(Icon.arrowRight, "arrow Right")
                }
            }
            scrollingHorizontally - row {
                forEachUpdating(tastyTreatsSnacks) { snack ->
                    snackCardGradientSquareBackground(snack, darkBlue, lightBlue)
                }
            }
            separator()
            row {
                expanding - h2(popularCollection?.name ?: "Snacks")
                PurpleIconColor.onNext - button {
                    icon(Icon.arrowRight, "arrow Right")
                }
            }
            scrollingHorizontally - row {
                forEachUpdating(popularOnJetsnackSnacks) { snack ->
                    snackItemAvatarStyle(snack)
                }
            }
            separator()
            row {
                expanding - h2(wfhFavsCollection?.name ?: "Snacks")
                PurpleIconColor.onNext - button {
                    icon(Icon.arrowRight, "arrow Right")
                }
            }
            scrollingHorizontally - row {
                forEachUpdating(wfhFavsSnacks) { snack ->
                    snackCardGradientSquareBackground(
                        snack,
                        Color.fromHexString("#a60b66"),
                        Color.fromHexString("#530abf")
                    )
                }
            }
            separator()
            row {
                expanding - h2(newlyAdded?.name ?: "Snacks")
                PurpleIconColor.onNext - button {
                    icon(Icon.arrowRight, "arrow Right")
                }
            }
            scrollingHorizontally - row {
                forEachUpdating(newlyAddedSnacks) { snack ->
                    snackItemAvatarStyle(snack)
                }
            }
            separator()
            row {
                expanding - h2(exclusiveCollection?.name ?: "Snacks")
                PurpleIconColor.onNext - button {
                    icon(Icon.arrowRight, "arrow Right")
                }
            }
            scrollingHorizontally - row {
                forEachUpdating(exclusiveSnacks) { snack ->
                    snackCardGradientSquareBackground(snack, darkBlue, lightBlue)
                }
            }
        }
    }
}

fun ViewWriter.snackCardGradientSquareBackground(
    snack: Readable<Snack>,
    firstGradientColor: Color,
    secondGradientColor: Color
): ViewModifiable {
    return SnackCardSemantic.onNext - link {
        padding = 0.rem
        sizeConstraints(width = 10.rem, height = 14.rem) - frame {
            ThemeDerivation.invoke {
                it.withBack(
                    cornerRadii = CornerRadii.PerCorner(
                        1.rem,
                        true,
                        true,
                        false,
                        false
                    )
                )
            }.onNext - GradientBackgroundSemantic(firstGradientColor, secondGradientColor).onNext - sizeConstraints(
                height = 7.rem
            ) - frame {}

            col {
                space()
                centered - sizeConstraints(width = 7.rem, height = 7.rem) - CircleIconSemantic(5.rem).onNext - image {
                    ::description {snack().name}
                    ::source { ImageRemote(snack().imageUrl) }
                    scaleType = ImageScaleType.Crop
                }
            }
            atBottom - col {
                gap = 0.2.rem
                paddingByEdge = Edges(1.0.rem, 0.0.rem, 1.0.rem, 1.0.rem)
                space()
                SnackLargeTitle.onNext - text {
                    ::content { snack().name }
                    wraps = false
                    ellipsis = true
                }
                TagLineTextSemantic.onNext - text {
                    gap = 0.0.rem
                    padding = 0.0.rem
                    ::content { snack().tagline }
                }
            }
        }
        ::to {
            { SnackDetailPage(snack().id) }
        }
    }
}

fun ViewWriter.snackItemAvatarStyle(snack: Readable<Snack>): ViewModifiable {
    return sizeConstraints(width = 8.rem, height = 10.rem) -
            link {
                padding = 0.5.rem
                gap = 0.rem
                col {
                    centered - sizeConstraints(
                        width = 7.rem,
                        height = 7.rem
                    ) - CircleIconSemantic(5.rem).onNext - image {
                        ::source {
                            ImageRemote(snack().imageUrl)
                        }
                        ::description {snack().name}
                        scaleType = ImageScaleType.Crop
                    }
                    centered - text {
                        ::content { snack().name }
                    }
                }
                ::to {
                    { SnackDetailPage(snack().id) }
                }
            }
}