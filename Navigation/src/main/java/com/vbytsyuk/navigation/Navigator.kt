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

    val activeScreen: Screen?
        get() = if (activeTabStack.isNotEmpty()) activeTabStack.peek() else null


    internal fun baseApply(command: NavigationCommand<Screen>) {
        when (command) {
            is ForwardCommand -> activeTabStack.push(command.destination!!)
            is BackCommand -> activeTabStack.pop()
            is ChangeTabCommand -> activeTab = command.tab
            else -> throw Navigator.UnsupportedCommandException("No such command")
        }
        apply(command)
    }

    abstract fun apply(command: NavigationCommand<Screen>)


    class UnsupportedCommandException(message: String) : UnsupportedOperationException(message)

    class IllegalTabException(message: String) : IllegalArgumentException(message)
}

