package xyz.malefic.staticsite.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexGrow
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import xyz.malefic.staticsite.styles.ActiveNavItemStyle
import xyz.malefic.staticsite.styles.DropdownButtonHoverStyle
import xyz.malefic.staticsite.styles.DropdownContentStyle
import xyz.malefic.staticsite.styles.DropdownItemHoverStyle
import xyz.malefic.staticsite.styles.DropdownItemStyle
import xyz.malefic.staticsite.styles.DropdownStyle
import xyz.malefic.staticsite.styles.NavBarStyle
import xyz.malefic.staticsite.styles.NavItemHoverStyle
import xyz.malefic.staticsite.styles.isCurrentPage
import xyz.malefic.staticsite.util.Pages
import kotlin.time.Duration.Companion.milliseconds
import com.varabyte.kobweb.compose.ui.graphics.Color as Kolor

@Layout
@Composable
fun NavBarLayout(content: @Composable () -> Unit) {
    val ctx = rememberPageContext()
    val currentRoute = ctx.route.path
    var isDropdownOpen by remember { mutableStateOf(false) }

    // Configuration: Maximum number of pages to show before overflow
    val maxVisiblePages = 4
    val allPages = Pages.entries
    val visiblePages = allPages.take(maxVisiblePages)
    val overflowPages = allPages.drop(maxVisiblePages)

    Column(Modifier.fillMaxSize()) {
        Box(
            NavBarStyle.toModifier(),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .maxWidth(1200.px)
                    .padding(0.px, 20.px),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Brand/Logo area (optional)
                Box(Modifier.flexGrow(1)) {
                    // Developers can customize this area
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    visiblePages.forEach { page ->
                        val isActive = page.isCurrentPage(currentRoute)
                        val pageRoute = page.route

                        Link(
                            path = pageRoute,
                            modifier =
                                if (isActive) {
                                    ActiveNavItemStyle.toModifier()
                                } else {
                                    NavItemHoverStyle.toModifier()
                                },
                        ) {
                            Text(page.value)
                        }
                    }

                    if (overflowPages.isNotEmpty()) {
                        Box(DropdownStyle.toModifier()) {
                            // Dropdown button
                            Div(
                                attrs = {
                                    onClick { isDropdownOpen = !isDropdownOpen }
                                },
                            ) {
                                Box(
                                    DropdownButtonHoverStyle.toModifier(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text("More")
                                        // Simple arrow indicator
                                        Box(
                                            Modifier
                                                .margin(left = 8.px)
                                                .fontSize(10.px),
                                        ) {
                                            Text(if (isDropdownOpen) "▲" else "▼")
                                        }
                                    }
                                }
                            }

                            if (isDropdownOpen) {
                                Box(DropdownContentStyle.toModifier()) {
                                    Column {
                                        overflowPages.forEach { page ->
                                            val isActive = page.isCurrentPage(currentRoute)
                                            val pageRoute = page.route

                                            Link(
                                                path = pageRoute,
                                                modifier =
                                                    if (isActive) {
                                                        DropdownItemStyle
                                                            .background(Kolor.rgba(13f, 110f, 253f, 0.1f))
                                                            .color(Color("#0d6efd"))
                                                            .fontWeight(600)
                                                    } else {
                                                        DropdownItemHoverStyle.toModifier()
                                                    },
                                            ) {
                                                Text(page.value)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Box(Modifier.fillMaxSize()) {
            content()
        }
    }

    LaunchedEffect(isDropdownOpen) {
        if (isDropdownOpen) {
            // You might want to add a click outside listener here
            // For simplicity, we'll just auto-close after a delay
            delay(5000.milliseconds)
            isDropdownOpen = false
        }
    }
}
