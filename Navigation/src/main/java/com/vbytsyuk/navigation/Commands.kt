package com.vbytsyuk.navigation

public open class NavigationCommand<Screen>(
    val destination: Screen? = null,
    val data: String? = null
)

/**
 * Direct transition to [destination] screen with [data]
 */
public final class ForwardCommand<Screen>(
    destination: Screen,
    data: String? = null
) : NavigationCommand<Screen>(destination, data)

/**
 * Return to previous screen
 */
public final class BackCommand<Screen> : NavigationCommand<Screen>()