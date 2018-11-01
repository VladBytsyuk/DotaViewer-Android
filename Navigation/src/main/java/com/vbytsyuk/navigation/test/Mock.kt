package com.vbytsyuk.navigation.test

import com.vbytsyuk.navigation.*
import java.util.*

internal data class MockScreen(val name: String)

internal class MockNavigator(val app: MockApp) : Navigator<MockScreen> {
    override fun apply(command: NavigationCommand<MockScreen>) {
        when (command) {
            is ForwardCommand -> app.screenStack.push(command.destination)
            is BackCommand -> app.screenStack.pop()
            else -> throw Navigator.UnsupportedCommandException("No such command")
        }
    }
}

internal class MockApp {
    val router: Router<MockScreen> = Router()

    init {
        router.navigator = MockNavigator(this)
    }

    internal var screenStack: Stack<MockScreen> = Stack()

    val currentScreen: MockScreen?
        get() = if (screenStack.isNotEmpty()) screenStack.peek() else null
}