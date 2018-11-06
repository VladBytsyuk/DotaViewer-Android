package com.vbytsyuk.navigation

public open class NavigationCommand(
    val destination: NavigationScreen? = null,
    val data: String? = null
)

/**
 * Direct transition to [destination] screen with [data]
 */
public final class ForwardCommand(
    destination: NavigationScreen,
    data: String? = null
) : NavigationCommand(destination, data)

/**
 * Return to previous screen
 */
public final class BackCommand : NavigationCommand()