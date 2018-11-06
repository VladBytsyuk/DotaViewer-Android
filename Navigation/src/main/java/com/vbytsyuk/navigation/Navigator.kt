package com.vbytsyuk.navigation

public interface Navigator {
    public fun apply(command: NavigationCommand)

    class UnsupportedCommandException(message: String) : UnsupportedOperationException(message)
}