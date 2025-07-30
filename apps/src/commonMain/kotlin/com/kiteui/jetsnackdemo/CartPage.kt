package com.kiteui.jetsnackdemo

import com.kiteui.jetsnackdemo.DescriptionTextSemantic.withBack
import com.kiteui.jetsnackdemo.DescriptionTextSemantic.withoutBack
import com.kiteui.jetsnackdemo.model.CartItem
import com.lightningkite.kiteui.Routable
import com.lightningkite.kiteui.models.Color
import com.lightningkite.kiteui.models.Icon
import com.lightningkite.kiteui.models.ImageRemote
import com.lightningkite.kiteui.models.ImageScaleType
import com.lightningkite.kiteui.models.ThemeDerivation
import com.lightningkite.kiteui.models.rem
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
import com.lightningkite.kiteui.views.atCenterEnd
import com.lightningkite.kiteui.views.bold
import com.lightningkite.kiteui.views.centered
import com.lightningkite.kiteui.views.direct.button
import com.lightningkite.kiteui.views.direct.col
import com.lightningkite.kiteui.views.direct.frame
import com.lightningkite.kiteui.views.direct.h2
import com.lightningkite.kiteui.views.direct.image
import com.lightningkite.kiteui.views.direct.link
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
import com.lightningkite.readable.invoke
import com.lightningkite.readable.shared
import getPriceString

@Routable("/my-cart")
class CartPage : Page {
    override fun ViewWriter.render(): ViewModifiable {

        return scrolling - col {
            unpadded - h2 {
                ::content {
                    "Order (${cart().size} items)"
                }
            }
            forEachUpdating(cart) { item ->
                row {
                    expanding - link {
                        gap = 0.0.rem
                        padding = 0.0.rem
                        expanding - row {
                            CircleIconSemantic(10.rem).onNext - sizeConstraints(width = 7.rem, height = 7.rem) - image {
                                ::description {
                                    item().snack.name
                                }
                                ::source {
                                    ImageRemote(item.invoke().snack.imageUrl)
                                }
                                scaleType = ImageScaleType.Crop

                            }
                            col {
                                gap = 0.0.rem
                                padding = 0.0.rem
                                SnackLargeTitle.onNext - text {
                                    ::content {
                                        item().snack.name
                                    }
                                }
                                TagLineTextSemantic.onNext - text {
                                    ::content {
                                        item().snack.tagline
                                    }
                                }
                                PriceTextSemantic.onNext - text {
                                    ::content {
                                        item().snack.getPriceString()
                                    }
                                }
                            }
                            expanding - frame {}
                        }
                        ::to {
                            { SnackDetailPage(item().snack.id) }
                        }
                    }
                    col {
                        gap = 0.0.rem
                        padding = 0.0.rem
                        atCenterEnd - button {
                            icon(Icon.close, "Remove")
                            onClick {
                                cart.set(cart.value.minus(item()))
                            }
                        }
                        row {
                            gap = 0.0.rem
                            padding = 0.0.rem
                            centered - bold - text("QTY")
                            button {
                                IconGradient.onNext - icon(Icon.circleRemove, "Remove")
                                onClick {
                                    if (item().amount > 1) {
                                        val cartMutableList = cart.value.toMutableList()
                                        val indexOfItemToUpdate = cartMutableList.indexOf(item())
                                        val updatedItem = CartItem(item().snack, item().amount + 1)
                                        cartMutableList.set(indexOfItemToUpdate, updatedItem)
                                        cart.set(cartMutableList)
                                    } else {
                                        cart.set(cart.value.minus(item()))
                                    }
                                }
                            }

                            PriceTextSemantic.onNext - centered - bold - text {
                                padding = 0.5.rem
                                ::content {
                                    item().amount.toString()
                                }
                            }
                            button {
                                IconGradient.onNext - icon(Icon.circleAdd, "Add")
                                onClick {
                                    val cartMutableList = cart.value.toMutableList()
                                    val indexOfItemToUpdate = cartMutableList.indexOf(item())
                                    val updatedItem = CartItem(item().snack, item().amount + 1)
                                    cartMutableList.set(indexOfItemToUpdate, updatedItem)
                                    cart.set(cartMutableList)
                                }
                            }
                        }
                    }
                }

            }
            unpadded - h2("Summary")
            row {
                expanding - text("Subtotal")
                text {
                    ::content {
                        "$${cart().sumOf { (it.snack.price / 100) * it.amount }}"
                    }
                }
            }
            row {
                ::shown { cart().isNotEmpty() }
                expanding - text("Shipping & handling")
                text {
                    content = "$3.61"
                }
            }
            separator()
            row {
                expanding - space()
                bold - text("Total")
                text {
                    ::content{
                        if (cart().isNotEmpty()) "$${cart().sumOf { it.snack.price * it.amount }.plus(3.69)}"
                        else "$0.00"
                    }
                }
            }
            separator()
            row {
                unpadded - expanding - h2(" Inspired By your Cart")
                PurpleIconColor.onNext - button {
                    icon(Icon.arrowRight, "arrow Right")
                }
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
            row {
                padding = 0.0.rem
                expanding - space()
                expanding - GradientBackgroundSemantic(lightBlue, darkBlue).onNext - button {
                    ThemeDerivation.invoke {
                        it.withoutBack(
                            foreground = Color.black
                        )
                    }.onNext - bold - text("Checkout")
                }
            }
        }
    }
}