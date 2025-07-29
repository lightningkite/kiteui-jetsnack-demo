package com.kiteui.jetsnackdemo

import com.kiteui.jetsnackdemo.model.CartItem
import com.lightningkite.kiteui.Routable
import com.lightningkite.kiteui.models.Icon
import com.lightningkite.kiteui.models.ImageRemote
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
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
import com.lightningkite.kiteui.views.direct.space
import com.lightningkite.kiteui.views.direct.text
import com.lightningkite.kiteui.views.expanding
import com.lightningkite.kiteui.views.forEachUpdating
import com.lightningkite.kiteui.views.important
import com.lightningkite.kiteui.views.l2.icon
import com.lightningkite.readable.invoke
import com.lightningkite.readable.shared

@Routable("/my-cart")
class CartPage : Page {
    override fun ViewWriter.render(): ViewModifiable {

        return scrolling - col {
            h2 {
                ::content {
                    "Order (${cart().size} items)"
                }
            }
            forEachUpdating(cart) { item ->
                link {
                    row {
                        image {
                            ::source {
                                ImageRemote(item.invoke().snack.imageUrl)
                            }
                        }
                        col {
                            text {
                                ::content {
                                    item().snack.name
                                }
                            }
                            text {
                                ::content {
                                    item().snack.tagline
                                }
                            }
                            text {
                                ::content {
                                    "$${item().snack.price}"
                                }
                            }
                        }
                        expanding - frame{}
                        col {
                            button {
                                icon(Icon.remove, "Remove")
                                onClick {
                                    cart.set(cart.value.minus(item()))
                                }
                            }
                            row {
                                text("QTY")
                                button {
                                    icon(Icon.add, "Add")
                                    onClick {
                                        val cartMutableList= cart.value.toMutableList()
                                        val indexOfItemToUpdate = cartMutableList.indexOf(item())
                                        val updatedItem = CartItem(item().snack, item().amount + 1)
                                        cartMutableList.set(indexOfItemToUpdate, updatedItem)
                                        cart.set(cartMutableList)
                                    }
                                }
                                text {
                                    ::content {
                                        item().amount.toString()
                                    }
                                }
                                button {
                                    icon(Icon.remove, "Remove")
                                    onClick {
                                        if(item().amount > 1) {
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
                            }
                        }
                    }
                    ::to {
                        {SnackDetailPage(item().snack.id)}
                    }
                }
            }
            h2("Summary")
            row {
                text("Subtotal")
                text {
                    ::content {
                        "$${cart().sumOf { it.snack.price * it.amount }}"
                    }
                }
            }
            row {
                text("Shipping & handling")
                text("$3.61")
            }
            separator()
            row {
                expanding - space()
                text{
                    ::content{
                        "$${cart().sumOf { it.snack.price * it.amount }.plus(3.69)}"
                    }
                }
            }
            separator()
            text("Inspired By your Cart")
            scrollingHorizontally - row {
                forEachUpdating(shared {
                    collections.find{it.id == 2L}?.let {
                        val collectionSnacks = it.snacks
                        snacks.filter {collectionSnacks.contains(it.id)}
                    }?:emptyList()}) { snack ->
                    snackCardSquareBackground(snack)
                }
            }
            row {
                expanding - space()
                important - button {
                    text("Checkout")

                }
            }
        }
    }
}