package com.vbytsyuk.navigation

open class NavigationCommand<out Screen>(
    val tab: Tab? = null,
    val destination: Screen? = null,
    val data: String? = null
)


/**
 * Direct transition to [destination] screen with [data]
 */
class ForwardCommand<out Screen>(
    destination: Screen,
    data: String? = null
) : NavigationCommand<Screen>(
    destination = destination,
    data = data
)


/**
 * Return to previous screen
 */
class BackCommand<out Screen> : NavigationCommand<Screen>()


/**
 * Change tab with screens to [tab]
 */
class ChangeTabCommand<out Screen>(
    tab: Tab
) : NavigationCommand<Screen>(tab = tab)