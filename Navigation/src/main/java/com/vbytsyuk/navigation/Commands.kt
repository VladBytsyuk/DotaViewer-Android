package com.vbytsyuk.navigation

interface NavigationCommand<out Screen>


/**
 * Direct transition to [destination] screen with [data]
 */
data class ForwardCommand<out Screen>(
    val destination: Screen,
    val data: String? = null
) : NavigationCommand<Screen>


/**
 * Return to previous screen
 */
class BackCommand<out Screen> : NavigationCommand<Screen>


/**
 * Change tab with screens to [tab]
 */
data class ChangeTabCommand<out Screen>(val tab: Tab) : NavigationCommand<Screen>