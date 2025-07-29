package com.kiteui.jetsnackdemo

import com.lightningkite.kiteui.Routable
import com.lightningkite.kiteui.models.Icon
import com.lightningkite.kiteui.navigation.Page
import com.lightningkite.kiteui.views.ViewModifiable
import com.lightningkite.kiteui.views.ViewWriter
import com.lightningkite.kiteui.views.centered
import com.lightningkite.kiteui.views.direct.col
import com.lightningkite.kiteui.views.direct.frame
import com.lightningkite.kiteui.views.direct.h1
import com.lightningkite.kiteui.views.direct.text
import com.lightningkite.kiteui.views.empty
import com.lightningkite.kiteui.views.l2.icon

@Routable("/profile")
class ProfilePage: Page {
    override fun ViewWriter.render(): ViewModifiable {

        return frame {
            centered - col {
                icon(Icon.empty,"Empty")
                h1("This is currently work in progress")
                text("Grab a beverage and Check back later!")
            }
        }
    }
}