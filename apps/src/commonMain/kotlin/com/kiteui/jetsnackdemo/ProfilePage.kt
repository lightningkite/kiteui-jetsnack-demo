package com.kiteui.jetsnackdemo

import com.lightningkite.kiteui.Routable
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
import com.lightningkite.kiteui.views.direct.col
import com.lightningkite.kiteui.views.direct.text

@Routable("/profile")
class ProfilePage: Page {
    override fun ViewWriter.render(): ViewModifiable {

        return col {
            text("Profile page")
        }
    }
}