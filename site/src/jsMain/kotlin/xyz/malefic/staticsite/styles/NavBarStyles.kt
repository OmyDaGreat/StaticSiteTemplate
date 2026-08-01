package xyz.malefic.staticsite.styles

import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textDecorationLine
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.whiteSpace
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.extendedBy
import com.varabyte.kobweb.silk.style.extendedByBase
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s

val NavBarStyle =
    CssStyle.base {
        Modifier
            .fillMaxWidth()
            .height(60.px)
            .background(AppTheme.static.surface.variable)
            .borderBottom(1.px, LineStyle.Solid, AppTheme.static.primary.variable)
    }

val NavItemStyle =
    CssStyle.base {
        Modifier
            .margin(leftRight = 4.px)
            .borderRadius(6.px)
            .padding(12.px, 20.px)
            .textDecorationLine(TextDecorationLine.None)
            .color(AppTheme.static.onSurface.variable)
            .fontSize(16.px)
            .fontWeight(500)
            .transition(Transition.all(0.2.s))
            .whiteSpace(WhiteSpace.NoWrap)
    }

val InactiveNavItemStyle =
    NavItemStyle.extendedBy {
        hover {
            Modifier
                .background(AppTheme.static.primaryTranslucent.variable)
                .color(AppTheme.static.onPrimary.variable)
        }
    }

val ActiveNavItemStyle =
    NavItemStyle.extendedByBase {
        Modifier
            .background(AppTheme.static.primary.variable)
            .color(AppTheme.static.onPrimary.variable)
            .fontWeight(600)
    }
