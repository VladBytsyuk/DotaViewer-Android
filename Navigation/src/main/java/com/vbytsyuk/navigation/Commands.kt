package com.vbytsyuk.navigation


sealed class NavigationCommand<out Screen> {

    /**
     * Direct transition to [destination] screen with [data]
     */
    data class Forward<out Screen>(
        val destination: Screen,
        val data: String? = null
    ) : NavigationCommand<Screen>()


    /**
     * Return to previous screen
     */
    object Back : NavigationCommand<Nothing>()


    /**
     * Change tab with screens to [tab]
     */
    data class ChangeTabCommand<out Screen>(val tab: Tab) : NavigationCommand<Screen>()
}