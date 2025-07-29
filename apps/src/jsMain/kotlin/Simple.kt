package com.lightningkite.jetsnackdemo

import com.kiteui.jetsnackdemo.AutoRoutes
import com.kiteui.jetsnackdemo.app
import com.kiteui.jetsnackdemo.appTheme
import com.lightningkite.kiteui.*
import com.lightningkite.kiteui.navigation.PageNavigator

fun main() {
    debugMode = true
    println("Booting up... ")
    root(appTheme) {
        app(PageNavigator { AutoRoutes }, PageNavigator { AutoRoutes })
    }
}
