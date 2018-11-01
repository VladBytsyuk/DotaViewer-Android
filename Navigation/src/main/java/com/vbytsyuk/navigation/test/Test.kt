package com.vbytsyuk.navigation.test

import org.junit.Test

class NavigatorTest {
    @Test
    fun testCommandsWithOneScreen() {
        val app = MockApp()
        val screenA = MockScreen("A")

        app.router.navigateTo(screenA)
        assert(app.currentScreen == screenA)
        
        app.router.back()
        assert(app.currentScreen == null)
    }

    @Test
    fun testCommandsWithTwoScreens() {
        val app = MockApp()
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        app.router.navigateTo(screenB)
        assert(app.currentScreen == screenB)

        app.router.back()
        assert(app.currentScreen == screenA)
        
        app.router.back()
        assert(app.currentScreen == null)
    }

    @Test
    fun testCommandsWithThreeScreens() {
        val app = MockApp()
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")
        val screenC = MockScreen("B")

        app.router.navigateTo(screenA)
        app.router.navigateTo(screenB)
        assert(app.currentScreen == screenB)

        app.router.back()
        assert(app.currentScreen == screenA)

        app.router.navigateTo(screenC)
        assert(app.currentScreen == screenC)

        app.router.back()
        assert(app.currentScreen == screenA)

        app.router.back()
        assert(app.currentScreen == null)
    }


    @Test
    fun testRemoveAddNavigatorBeforeNavigationWithOneFragment() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")

        app.router.navigator = null
        app.router.navigateTo(screenA)
        assert(app.currentScreen == null)

        app.router.navigator = navigator
        assert(app.currentScreen == screenA)
    }

    @Test
    fun testRemoveAddNavigatorBeforeNavigationWithTwoFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigator = null
        app.router.navigateTo(screenA)
        app.router.navigateTo(screenB)
        assert(app.currentScreen == null)

        app.router.navigator = navigator
        assert(app.currentScreen == screenB)
    }

    @Test
    fun testRemoveAddNavigatorDuringNavigationWithTwoFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        assert(app.currentScreen == screenA)

        app.router.navigator = null
        assert(app.currentScreen == screenA)

        app.router.navigateTo(screenB)
        assert(app.currentScreen == screenA)

        app.router.navigator = navigator
        assert(app.currentScreen == screenB)
    }

    @Test
    fun testRemoveAddNavigatorDuringNavigationWithThreeFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")
        val screenC = MockScreen("C")

        app.router.navigateTo(screenA)
        assert(app.currentScreen == screenA)

        app.router.navigator = null
        assert(app.currentScreen == screenA)

        app.router.navigateTo(screenB)
        assert(app.currentScreen == screenA)

        app.router.navigator = navigator
        assert(app.currentScreen == screenB)

        app.router.back()
        assert(app.currentScreen == screenA)

        app.router.navigator = null
        assert(app.currentScreen == screenA)

        app.router.navigator = navigator
        assert(app.currentScreen == screenA)

        app.router.navigateTo(screenC)
        assert(app.currentScreen == screenC)
    }

    @Test
    fun testRemoveAddNavigatorAfterNavigationWithTwoFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        assert(app.currentScreen == screenA)

        app.router.navigateTo(screenB)
        assert(app.currentScreen == screenB)

        app.router.navigator = null
        assert(app.currentScreen == screenB)

        app.router.back()
        assert(app.currentScreen == screenB)

        app.router.navigator = navigator
        assert(app.currentScreen == screenA)
    }
}