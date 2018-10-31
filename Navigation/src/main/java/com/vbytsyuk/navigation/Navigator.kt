package com.vbytsyuk.navigation

public interface Navigator<Screen> {
    public fun apply(command: NavigationCommand<Screen>)

    class UnsupportedCommandException(message: String) : UnsupportedOperationException(message)
}