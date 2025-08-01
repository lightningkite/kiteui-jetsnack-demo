package com.kiteui.jetsnackdemo

import com.kiteui.jetsnackdemo.model.CartItem
import com.lightningkite.kiteui.Platform
import com.lightningkite.kiteui.current
import com.lightningkite.kiteui.models.*
import com.lightningkite.kiteui.models.AffirmativeSemantic.withBack
import com.lightningkite.kiteui.navigation.PageNavigator
import com.lightningkite.kiteui.navigation.mainPageNavigator
import com.lightningkite.kiteui.reactive.*
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
import com.lightningkite.kiteui.views.bold
import com.lightningkite.kiteui.views.centered
import com.lightningkite.kiteui.views.direct.col
import com.lightningkite.kiteui.views.direct.frame
import com.lightningkite.kiteui.views.direct.h1
import com.lightningkite.kiteui.views.direct.h2
import com.lightningkite.kiteui.views.direct.icon
import com.lightningkite.kiteui.views.direct.link
import com.lightningkite.kiteui.views.direct.row
import com.lightningkite.kiteui.views.direct.rowCollapsingToColumn
import com.lightningkite.kiteui.views.direct.shownWhen
import com.lightningkite.kiteui.views.direct.text
import com.lightningkite.kiteui.views.direct.unpadded
import com.lightningkite.kiteui.views.dynamicTheme
import com.lightningkite.kiteui.views.expanding
import com.lightningkite.kiteui.views.l2.*
import com.lightningkite.reactive.context.*
import com.lightningkite.reactive.core.*
import com.lightningkite.reactive.extensions.*
import com.lightningkite.reactive.lensing.*
import com.lightningkite.readable.*

val appTheme = Signal<Theme>(jetsnackDark)

// Notification Items
val fcmToken: Signal<String?> = Signal(null)
val setFcmToken =
    { token: String -> fcmToken.value = token } //This is for iOS. It is used in the iOS app. Do not remove.


val mainNavPages = listOf(
    NavLink(title = { "Home" }, icon = { Icon.home }) { { HomePage() } },
    NavLink(title = { "Search" }, icon = { Icon.search }) { { SearchPage() } },
    NavLink(title = { "My Cart" }, icon = { Icon.cart }) { { CartPage() } },
    NavLink(title = { "Profile" }, icon = { Icon.person }) { { ProfilePage() } },
)

val cart = Signal<List<CartItem>>(listOf())

fun ViewWriter.app(navigator: PageNavigator, dialog: PageNavigator): ViewModifiable {


    navigator.navigate(HomePage())

    return MainContentSemantic.onNext - appBase(navigator, dialog) {
        padding = 0.0.rem
        gap = 0.rem
        applySafeInsets(bottom = false)
        OuterSemantic.onNext - col {
            shownWhen { mainPageNavigator.currentPage() !is UseFullPage } - bold - centered  - h1{
                content = "Jetsnack powered by KiteUi"
            align = Align.Center
            }
            expanding - navigatorView(navigator)//navigatorView1(navigator)
            shownWhen { mainPageNavigator.currentPage() !is UseFullPage } - bottomBar(mainNavPages)
        }
    }
}


fun ViewWriter.bottomBar(navItems: List<NavLink>): ViewModifiable {
    return unpadded - ThemeDerivation.invoke {
        it.withBack(
            background = Color.fromHexString("#c5bfdf"),
            cornerRadii = CornerRadii.ForceConstant(0.0.rem),
            foreground = Color.black
        )
    }.onNext - row {
        padding = 0.5.rem
        gap = 0.0.dp
        for (navLink in navItems) {
            expanding - link {
                padding = 0.0.rem
                gap = 0.5.rem
                dynamicTheme {
                    val matchingScreen = mainPageNavigator.currentPage()
                        ?.let {
                            mainPageNavigator.routes.render(it)
                        }?.urlLikePath?.segments == mainPageNavigator.routes.render(
                        navLink.destination.invoke(this)()
                    )?.urlLikePath?.segments
                    if (matchingScreen) ThemeDerivation.invoke {
                        it.withBack(
                            outline = Color.black,
                            outlineWidth = 2.px,
                            cornerRadii = CornerRadii.ForceConstant(2.0.rem),
                        )
                    } else null
                }
                centered - rowCollapsingToColumn(40.rem) {
                    padding = 0.5.rem
                    gap = 0.5.rem
                    shownWhen { navLink.hidden?.invoke() != true }
                    centered  - icon {
                        ::source { navLink.icon().copy(width = 1.5.rem, height = 1.5.rem) }
                        ::description { navLink.title() }
                    }
                    shownWhen {
                        Platform.current == Platform.Android ||
                        mainPageNavigator.currentPage()
                            ?.let {
                                mainPageNavigator.routes.render(it)
                            }?.urlLikePath?.segments == mainPageNavigator.routes.render(
                            navLink.destination.invoke(this)()
                        )?.urlLikePath?.segments
                    } - centered  - text {
                        ::content {
                            navLink.title(this)
                        }
                        align = Align.Center
                    }
                }
                ::to {
//                    println("DEBUG navLink ${navLink.destination}")
                    navLink.destination() }

            }
            shownWhen {
                mainPageNavigator.currentPage()
                    ?.let {
                        mainPageNavigator.routes.render(it)
                    }?.urlLikePath?.segments != mainPageNavigator.routes.render(
                    navLink.destination.invoke(this)()
                )?.urlLikePath?.segments
            } - expanding - frame {}
        }
    }
}

interface UseFullPage
