package com.vbytsyuk.navigation.test

import com.vbytsyuk.navigation.NavigationCommand
import com.vbytsyuk.navigation.Navigator
import com.vbytsyuk.navigation.Router
import com.vbytsyuk.navigation.Tab

internal data class MockScreen(val name: String)

internal enum class MockTab : Tab { First, Second }


internal class MockNavigator(
    val app: MockApp,
    tabsToRoot: Map<Tab, MockScreen> = mapOf(TabStub() to MockScreen("Root"))
) : Navigator<MockScreen>(tabsToRoot) {
    override fun apply(command: NavigationCommand<MockScreen>) {
        when (command) {
            is NavigationCommand.Forward -> activeTabStack.push(command.destination)
            is NavigationCommand.Back -> activeTabStack.pop()
            is NavigationCommand.ChangeTabCommand -> activeTab = command.tab
            else -> throw Navigator.UnsupportedCommandException("No such command")
        }
        app.currentScreen = activeScreen
    }
}


internal class MockApp(isMultipleTabs: Boolean = false) {
    val router: Router<MockScreen> = Router()

    init {
        val tabsToRoot = mapOf(
            MockTab.First as Tab to MockScreen("First root"),
            MockTab.Second as Tab to MockScreen("Second root")
        )
        val navigator = if (isMultipleTabs) {
            MockNavigator(app = this, tabsToRoot = tabsToRoot)
        } else {
            MockNavigator(app = this)
        }
        router.navigator = navigator

    }

    var currentScreen: MockScreen? = null
}