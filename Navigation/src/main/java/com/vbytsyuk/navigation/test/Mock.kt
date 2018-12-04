package com.vbytsyuk.navigation.test

import com.vbytsyuk.navigation.*

internal data class MockScreen(val name: String)

internal enum class MockTab : Tab { First, Second }


internal class MockNavigator(
    val app: MockApp,
    tabs: List<Tab> = listOf(TabStub())
) : Navigator<MockScreen>(tabs) {
    override fun apply(command: NavigationCommand<MockScreen>) {
        when (command) {
            is ForwardCommand -> activeTabStack.push(command.destination as MockScreen)
            is BackCommand -> activeTabStack.pop()
            is ChangeTabCommand -> activeTab = command.tab
            else -> throw Navigator.UnsupportedCommandException("No such command")
        }
        app.currentScreen = activeScreen
    }
}


internal class MockApp(isMultipleTabs: Boolean = false) {
    val router: Router<MockScreen> = Router()

    init {
        val tabsList = MockTab.values().toList()
        val navigator = if (isMultipleTabs)
            MockNavigator(app = this, tabs = tabsList)
        else
            MockNavigator(app = this)
        router.navigator = navigator

    }

    var currentScreen: MockScreen? = null
}