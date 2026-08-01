package xyz.malefic.staticsite.styles

import com.varabyte.kobweb.compose.ui.graphics.Colors
import xyz.malefic.kutint.BasePalette
import xyz.malefic.kutint.PaletteDefinition
import xyz.malefic.kutint.color
import xyz.malefic.kutint.darkTransform
import xyz.malefic.kutint.kutint
import xyz.malefic.kutint.parseHex

class AppPalette : BasePalette() {
    val primary by color(parseHex("#3f51b5") darkTransform { it.lighten(0.3f) })
    val secondary by color(parseHex("#f50057") darkTransform { it.lighten(0.3f) })

    val onPrimary by color(Colors.White.kutint, Colors.Black.kutint)
    val onSecondary by color(Colors.White.kutint, Colors.Black.kutint)

    val primaryTranslucent by color(primary map { it.withAlpha(0.5f) })
    val secondaryTranslucent by color(secondary map { it.withAlpha(0.5f) })

    val background by color(parseHex("#fafafa"), parseHex("#121212"))
    val onBackground by color(Colors.Black.kutint, Colors.White.kutint)

    val surface by color(Colors.White.kutint, parseHex("#1e1e1e"))
    val onSurface by color(Colors.Black.kutint, Colors.White.kutint)
}

object AppTheme : PaletteDefinition<AppPalette>(AppPalette())
