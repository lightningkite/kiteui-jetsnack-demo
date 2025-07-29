package com.kiteui.jetsnackdemo

import com.kiteui.jetsnackdemo.DescriptionTextSemantic.withBack
import com.kiteui.jetsnackdemo.model.CartItem
import com.lightningkite.kiteui.Routable
import com.lightningkite.kiteui.models.CornerRadii
import com.lightningkite.kiteui.models.Edges
import com.lightningkite.kiteui.models.Icon
import com.lightningkite.kiteui.models.ImageRemote
import com.lightningkite.kiteui.models.ImageScaleType
import com.lightningkite.kiteui.models.ThemeDerivation
import com.lightningkite.kiteui.models.rem
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.navigation.mainPageNavigator
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
import com.lightningkite.kiteui.views.atTopStart
import com.lightningkite.kiteui.views.bold
import com.lightningkite.kiteui.views.card
import com.lightningkite.kiteui.views.centered
import com.lightningkite.kiteui.views.direct.button
import com.lightningkite.kiteui.views.direct.col
import com.lightningkite.kiteui.views.direct.frame
import com.lightningkite.kiteui.views.direct.h2
import com.lightningkite.kiteui.views.direct.image
import com.lightningkite.kiteui.views.direct.onClick
import com.lightningkite.kiteui.views.direct.row
import com.lightningkite.kiteui.views.direct.scrolling
import com.lightningkite.kiteui.views.direct.scrollingHorizontally
import com.lightningkite.kiteui.views.direct.separator
import com.lightningkite.kiteui.views.direct.sizeConstraints
import com.lightningkite.kiteui.views.direct.space
import com.lightningkite.kiteui.views.direct.text
import com.lightningkite.kiteui.views.direct.unpadded
import com.lightningkite.kiteui.views.expanding
import com.lightningkite.kiteui.views.forEachUpdating
import com.lightningkite.kiteui.views.important
import com.lightningkite.kiteui.views.l2.icon
import com.lightningkite.readable.Property
import com.lightningkite.readable.invoke
import com.lightningkite.readable.shared
import getPriceString
import kotlinx.coroutines.launch

@Routable("/snack-detail/{id}")
class SnackDetailPage(val id: Long) : Page, UseFullPage {
    override fun ViewWriter.render(): ViewModifiable {
        val snack = snacks.firstOrNull() { it.id == id }
        if (snack == null) throw IllegalArgumentException("No snack with id $id")
        val quantity = Property(1)
        return col {
            padding = 0.0.rem
            launch {
                cart().find { it.snack.id == id }?.let {
                    quantity.set(it.amount)
                }
            }
            unpadded - scrolling - expanding - col {
                padding = 1.rem
                sizeConstraints(height = 25.rem) - frame {

                    GradientBackgroundSemantic(darkBlue, lightBlue).onNext - sizeConstraints(height = 15.rem) - frame {
                        padding = 0.0.rem
                    }
                    centered - sizeConstraints(
                        width = 20.rem,
                        height = 20.rem
                    ) - CircleIconSemantic(10.rem).onNext - image {
                        source = ImageRemote(snack.imageUrl)
                        scaleType = ImageScaleType.Crop
                    }
                    atTopStart - button {
                        icon(Icon.arrowBack, "Arrow back")
                        onClick {
                            mainPageNavigator.goBack()
                        }
                    }
                }
                col {
                    gap = 0.2.rem
                    SnackLargeTitle.onNext - text(snack.name)
                    TagLineTextSemantic.onNext - text(snack.tagline)
                    PriceTextSemantic.onNext - text(snack.getPriceString())
                }
                separator { }
                DescriptionTextSemantic.onNext - text("Detail")


                val expandDescriptionToggle = Property(false)
                DescriptionTextSemantic.onNext - text {
                    ::content {
                        if (expandDescriptionToggle()) {
                            "Lorem Ipsum Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. " +
                                    "Lorem Ipsum Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum." +
                                    "Lorem Ipsum Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                        } else {
                            "Lorem Ipsum Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                        }
                    }
                }
                centered - button {
                    bold - TextLightGreen.onNext - text {
                        ::content {
                            if (expandDescriptionToggle()) "SEE LESS" else "SEE MORE"
                        }
                    }
                    onClick {
                        expandDescriptionToggle.set(!expandDescriptionToggle.value)
                    }
                }
                DescriptionTextSemantic.onNext - text("Ingredients")
                DescriptionTextSemantic.onNext - text("Vanilla, Almond Eggs, Butter, Cream, Sugar")
                separator()
                h2 {
                    content = "Customers also bought"
                    paddingByEdge = Edges(0.0.rem, 1.0.rem, 0.0.rem, 0.0.rem)
                }
                scrollingHorizontally - row {
                    forEachUpdating(shared { snacks.shuffled().subList(0, 5) }) { snack ->
                        snackItemAvatarStyle(snack)
                    }
                }

                h2 {
                    content = "Popular on Jetsnack"
                    paddingByEdge = Edges(0.0.rem, 0.5.rem, 0.0.rem, 0.0.rem)
                }
                scrollingHorizontally - row {
                    forEachUpdating(shared {
                        collections.find { it.id == 2L }?.let {
                            val collectionSnacks = it.snacks
                            snacks.filter { collectionSnacks.contains(it.id) }
                        } ?: emptyList()
                    }) { snack ->
                        snackItemAvatarStyle(snack)
                    }
                }
            }
            card - row {
                gap = 0.5.rem
                padding = 0.5.rem
                space()
                centered - bold - text("QTY")
                button {
                    padding = 0.5.rem
                    IconGradient.onNext - icon(Icon.circleRemove, "Remove")
                    onClick {
                        if (quantity.value > 1) quantity.set(quantity.value - 1)
                    }
                }

                PriceTextSemantic.onNext - centered - bold - text {
                    padding = 0.5.rem

                    ::content {
                        quantity().toString()
                    }
                }
                button {
                    padding = 0.5.rem

                    IconGradient.onNext - icon(Icon.circleAdd, "Add")
                    onClick {
                        quantity.set(quantity.value + 1)
                    }
                }
                ThemeDerivation.invoke {
                    it.withBack(
                        cornerRadii = CornerRadii.ForceConstant(2.rem)
                    )
                }.onNext - GradientBackgroundSemantic(lightBlue, darkBlue).onNext - expanding - button {
                    padding = 0.5.rem

                    centered - text {
                        ::content {
                            if (cart().find { it.snack.id == id } == null) "ADD TO CART" else "UPDATE CART"
                        }
                    }
                    onClick {
                        if (cart().find { it.snack.id == id } == null) {
                            cart.set(cart.value.plus(CartItem(snack, 1)))
                        } else {
                            val updatedCart = cart.value.find { it.snack.id == id }
                                ?.let { itemToRemove -> cart.value.minus(itemToRemove) }
                                ?.let { updatedCart ->
                                    if (quantity.value > 1) updatedCart.plus(CartItem(snack, quantity.value))
                                    cart.set(updatedCart)
                                }
                        }
                    }
                }
            }
        }
    }
}