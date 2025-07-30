package com.kiteui.jetsnackdemo

import android.os.Bundle
import com.lightningkite.kiteui.KiteUiActivity
import com.lightningkite.kiteui.Throwable_report
import com.lightningkite.kiteui.models.Theme
import com.lightningkite.kiteui.navigation.PageNavigator
import com.lightningkite.kiteui.printStackTrace2
import com.lightningkite.readable.ReactiveContext

class MainActivity : KiteUiActivity() {
    companion object {
        val main = PageNavigator { AutoRoutes }
        val dialog = PageNavigator { AutoRoutes }
    }

    override val theme: ReactiveContext.() -> Theme
        get() = { appTheme() }

    override val mainNavigator: PageNavigator get() = main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeCacheDir.setReadOnly()

        with(viewWriter) {
            app(main, dialog)
        }
    }
}
