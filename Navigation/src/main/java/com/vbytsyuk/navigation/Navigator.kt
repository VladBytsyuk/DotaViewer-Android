package com.vbytsyuk.navigation

import containers.AriadneStack
import containers.Stack


public abstract class Navigator<Screen>(tabsToRoot: Map<Tab, Screen>) {
    internal class TabStub : Tab

    private val tabStacks: Map<Tab, Stack<Screen>> =
        tabsToRoot.map { it.key to AriadneStack<Screen>().apply { push(it.value) } }.toMap()

    var activeTab: Tab? = tabStacks.keys.first()

    val activeTabStack: Stack<Screen>
        get() = tabStacks[activeTab]
            ?: throw IllegalTabException("No such tab = [$activeTab] registered in Router.")

    val activeScreen: Screen get() = activeTabStack.peek()


    internal fun baseApply(command: NavigationCommand<Screen>) {
        when (command) {
            is ForwardCommand -> activeTabStack.push(command.destination)
            is BackCommand -> if (activeTabStack.size > 1) activeTabStack.pop()
            is ChangeTabCommand -> activeTab = command.tab
        }
        apply(command)
    }

    abstract fun apply(command: NavigationCommand<Screen>)


    class UnsupportedCommandException(message: String) : UnsupportedOperationException(message)

    class IllegalTabException(message: String) : IllegalArgumentException(message)
}

