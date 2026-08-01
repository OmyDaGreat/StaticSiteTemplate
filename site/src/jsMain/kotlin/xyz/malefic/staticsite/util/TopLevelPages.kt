package xyz.malefic.staticsite.util

enum class TopLevelPages(
    val value: String,
    val route: String,
) {
    INDEX("Index", "/"),
    ABOUT("About", "/about"),
    ;

    fun isCurrentPage(currentRoute: String): Boolean {
        fun String.normalize() = "/" + this.trim().removePrefix("/").removeSuffix("/")
        return currentRoute.normalize() == route.normalize()
    }
}
