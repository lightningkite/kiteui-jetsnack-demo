package com.kiteui.jetsnackdemo

import com.lightningkite.kiteui.models.Icon
import com.lightningkite.kiteui.models.rem

val Icon.Companion.filter: Icon
    get() = Icon(
        width = 1.5.rem,
        height = 1.5.rem,
        viewBoxMinX = 0,
        viewBoxMinY = -960,
        viewBoxWidth = 960,
        viewBoxHeight = 960,
        pathDatas = listOf("M400-240v-80h160v80H400ZM240-440v-80h480v80H240ZM120-640v-80h720v80H120Z")
    )

val Icon.Companion.arrowRight: Icon
    get() = Icon(
        width = 1.5.rem,
        height = 1.5.rem,
        viewBoxMinX = 0,
        viewBoxMinY = -960,
        viewBoxWidth = 960,
        viewBoxHeight = 960,
        pathDatas = listOf("M647-440H160v-80h487L423-744l57-56 320 320-320 320-57-56 224-224Z")
    )

val Icon.Companion.cart: Icon
    get()= Icon(
        width = 1.5.rem,
        height = 1.5.rem,
        viewBoxMinX = 0,
        viewBoxMinY = -960,
        viewBoxWidth = 960,
        viewBoxHeight = 960,
        pathDatas = listOf("M280-80q-33 0-56.5-23.5T200-160q0-33 23.5-56.5T280-240q33 0 56.5 23.5T360-160q0 33-23.5 56.5T280-80Zm400 0q-33 0-56.5-23.5T600-160q0-33 23.5-56.5T680-240q33 0 56.5 23.5T760-160q0 33-23.5 56.5T680-80ZM246-720l96 200h280l110-200H246Zm-38-80h590q23 0 35 20.5t1 41.5L692-482q-11 20-29.5 31T622-440H324l-44 80h480v80H280q-45 0-68-39.5t-2-78.5l54-98-144-304H40v-80h130l38 80Zm134 280h280-280Z")
    )

val Icon.Companion.circleAdd: Icon
    get()= Icon(
        width = 1.5.rem,
        height = 1.5.rem,
        viewBoxMinX = 0,
        viewBoxMinY = -960,
        viewBoxWidth = 960,
        viewBoxHeight = 960,
        pathDatas = listOf("M440-280h80v-160h160v-80H520v-160h-80v160H280v80h160v160Zm40 200q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z")
    )
val Icon.Companion.circleRemove: Icon
    get()= Icon(
        width = 1.5.rem,
        height = 1.5.rem,
        viewBoxMinX = 0,
        viewBoxMinY = -960,
        viewBoxWidth = 960,
        viewBoxHeight = 960,
        pathDatas = listOf("M280-440h400v-80H280v80ZM480-80q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z")
    )