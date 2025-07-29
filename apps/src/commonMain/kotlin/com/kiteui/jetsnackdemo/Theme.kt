package com.kiteui.jetsnackdemo

import com.lightningkite.kiteui.models.ButtonSemantic
import com.lightningkite.kiteui.models.CardSemantic
import com.lightningkite.kiteui.models.Color
import com.lightningkite.kiteui.models.CornerRadii
import com.lightningkite.kiteui.models.DialogSemantic
import com.lightningkite.kiteui.models.Dimension
import com.lightningkite.kiteui.models.Edges
import com.lightningkite.kiteui.models.FieldSemantic
import com.lightningkite.kiteui.models.FocusSemantic
import com.lightningkite.kiteui.models.GradientStop
import com.lightningkite.kiteui.models.HeaderSemantic
import com.lightningkite.kiteui.models.HeaderSizeSemantic
import com.lightningkite.kiteui.models.LinearGradient
import com.lightningkite.kiteui.models.OuterSemantic
import com.lightningkite.kiteui.models.SelectedSemantic
import com.lightningkite.kiteui.models.Semantic
import com.lightningkite.kiteui.models.Theme
import com.lightningkite.kiteui.models.ThemeAndBack
import com.lightningkite.kiteui.models.darken
import com.lightningkite.kiteui.models.div
import com.lightningkite.kiteui.models.dp
import com.lightningkite.kiteui.models.px
import com.lightningkite.kiteui.models.rem

val purple = Color.fromHexString("#ada9de")
val greenishBlue = Color.fromHexString("#79d7db")
val jetsnackDark = Theme(
    id = "beemee",
//    font = FontAndStyle(font = systemDefaultFont, weight = 500),
    elevation = 0.dp,
    gap = 1.rem,
    padding = Edges(1.rem),
    cornerRadii = CornerRadii.RatioOfSpacing(1f),
    background = Color.fromHexString("#191a1a"),
    foreground = Color.white,
    separatorOverride = Color.gray(0.3f),
    derivations = mapOf(
        HeaderSemantic to {
            it.withoutBack(
                foreground = if (it.background.closestColor().perceivedBrightness > 0.5f) Color.black else Color.white
            )
        },
        OuterSemantic to {
            it.withBack(
                cascading = false,
                gap = 0.px,
                padding = Edges.ZERO,
                background = Color.transparent
            )
        },
        ButtonSemantic to {
            it.withBack(
                elevation = 0.dp,
                outline = Color.gray.applyAlpha(0.5f),
                outlineWidth = 1.dp,
                cornerRadii = CornerRadii.ForceConstant(100.rem)
            )
        },
        HeaderSizeSemantic(1) to {
            it.withBack(
                false,
                font =it.font.copy(size = 1.rem),
                 foreground =  Color.white,
            )
        },
        HeaderSizeSemantic(2) to {
            it.withBack(
                font =it.font.copy(size = 1.2.rem, weight = 600),
                foreground =  purple,
            )
        },
        HeaderSizeSemantic(3) to {
            it.withBack(
                font =it.font.copy(size = 1.0.rem, weight = 600),
            )
        },
        FieldSemantic to {
            it.withBack(
                background = Color.gray(0.2f),
                cornerRadii =  CornerRadii.ForceConstant(100.rem),
            )
        },
        FocusSemantic to {
            it.withBack(
                outline = it.outline
            )
        },
        SelectedSemantic to {
            it.withBack(
                background = LinearGradient(
                    listOf(
                        GradientStop(
                            0f,
                            purple
                        ),

                        GradientStop(                        1f
                            ,greenishBlue)
                    ),
                )
            )
        },
        CardSemantic to {
            it.withBack(
                outline = it.outline.darken(0.1f),
                outlineWidth = 1.dp
            )
        },
        DialogSemantic to {
            it.withBack(
                background = Color.fromHexString("#191a1a"),
            )
        },
    )
)

data object FilterCard: Semantic ("filter-card-semantic") {
    override fun default(theme: Theme) : ThemeAndBack {
        return theme.withBack(
            outline = purple,
            outlineWidth = 2.px,
            cornerRadii =  CornerRadii.ForceConstant(100.rem)
        )
    }
}

data object PurpleIconColor : Semantic ("purple-icon-color") {
    override fun default(theme: Theme) : ThemeAndBack {
        return theme.withBack(
            iconOverride = purple
        )
    }
}

data object SnackCardSemantic : Semantic("snack-card-semantic") {
    override fun default(theme: Theme): ThemeAndBack {
        return theme.withBack(
            background = Color.fromHexString("#2e2e2e"),
        )
    }
}

data object SnackLargeTitle : Semantic("snack-large-title") {
    override fun default(theme: Theme): ThemeAndBack {
        return theme.withoutBack(
            font =theme.font.copy(size = 1.3.rem, weight = 600),

        )

    }
}

val darkBlue = Color.fromHexString("#4539d3")
val lightBlue = Color.fromHexString("#79d7db")

data class GradientBackgroundSemantic(val color1: Color, val color2: Color) : Semantic("gradient-background-${color1.toInt()}-${color2.toInt()}") {

    override fun default(theme: Theme): ThemeAndBack {
        return theme.withBack(
            background = LinearGradient(
                listOf(
                    GradientStop(                        0f
                        , color1),
                    GradientStop(
                        1f,
                        color2
                    )

                ),
            )
        )
    }
}




data object IconGradient : Semantic("icon-gradient") {
    override fun default(theme: Theme): ThemeAndBack {
        return theme.withoutBack(
            iconOverride = LinearGradient(
                listOf(
                    GradientStop(
                        0f,
                        Color.fromHexString("#68cbdb")
                    ),

                    GradientStop(                        1f
                        , Color.fromHexString("#a1b0df"))
                ),
            ),
        )
    }
}

data class CircleIconSemantic(val size: Dimension) :
    Semantic("usericon-${size.value.toString().filter { it.isLetterOrDigit() }}") {
    override fun default(theme: Theme): ThemeAndBack = theme.withBack(
        cornerRadii = CornerRadii.ForceConstant(999.px),
        font = theme.font.copy(size = size / 2),
        padding = Edges(0.px)
    )
}

data object TagLineTextSemantic : Semantic("tagline-text") {
    override fun default(theme: Theme): ThemeAndBack {
        return theme.withoutBack(
            font = theme.font.copy(size = 1.rem, weight = 600),
            foreground = Color.gray(0.7f)
        )
    }
}

data object PriceTextSemantic : Semantic("price-text") {
    override fun default(theme: Theme): ThemeAndBack {
        return theme.withoutBack(
            font = theme.font.copy(size = 1.2.rem, weight = 600),
            foreground = purple,
        )
    }
}

data object DescriptionTextSemantic : Semantic("description-text") {
    override fun default(theme: Theme): ThemeAndBack {
        return theme.withoutBack(
            font = theme.font.copy(size = 0.8.rem, weight = 600),
            foreground = Color.gray(0.7f)
        )
    }
}

data object TextLightGreen: Semantic("text-light-green") {
    override fun default(theme: Theme): ThemeAndBack {
        return theme.withoutBack(
            foreground = greenishBlue
        )
    }
}

data class TestCircleIconSemantic(val size: Dimension) :
    Semantic("test-usericon-${size.value.toString().filter { it.isLetterOrDigit() }}") {
    override fun default(theme: Theme): ThemeAndBack = theme.withBack(
        cornerRadii = CornerRadii.PerCorner(
            100.rem,
            true,
            false,
            true,
            false
        ),
        font = theme.font.copy(size = size / 2),
        padding = Edges(0.px)
    )
}