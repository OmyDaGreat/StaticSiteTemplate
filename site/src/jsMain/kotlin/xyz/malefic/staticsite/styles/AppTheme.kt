package xyz.malefic.staticsite.styles

import xyz.malefic.kutint.BasePalette
import xyz.malefic.kutint.PaletteDefinition
import xyz.malefic.kutint.color
import xyz.malefic.kutint.darkTransform
import xyz.malefic.kutint.parseHex

class AppPalette : BasePalette() {
    val primary by color(parseHex("#3f51b5") darkTransform { it.lighten(0.3f) })
    val secondary by color(parseHex("#f50057") darkTransform { it.lighten(0.3f) })

    val background by color(parseHex("#fafafa"), parseHex("#121212"))
    val onBackground by color(parseHex("#000000"), parseHex("#ffffff"))

    val surface by color(parseHex("#ffffff"), parseHex("#1e1e1e"))
    val onSurface by color(parseHex("#000000"), parseHex("#ffffff"))
}

object AppTheme : PaletteDefinition<AppPalette>(AppPalette())
