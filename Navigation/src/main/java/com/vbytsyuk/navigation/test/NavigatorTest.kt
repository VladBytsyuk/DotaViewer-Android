package com.vbytsyuk.navigation.test

import junit.framework.Assert.assertEquals
import org.junit.Test

class NavigatorTest {
    @Test
    fun testCommandsWithOneScreen() {
        val app = MockApp()
        val screenA = MockScreen("A")

        app.router.navigateTo(screenA)
        assertEquals(app.currentScreen, screenA)
        
        app.router.back()
        assertEquals(app.currentScreen, null)
    }

    @Test
    fun testCommandsWithTwoScreens() {
        val app = MockApp()
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        app.router.navigateTo(screenB)
        assertEquals(app.currentScreen, screenB)

        app.router.back()
        assertEquals(app.currentScreen, screenA)
        
        app.router.back()
        assertEquals(app.currentScreen, null)
    }

    @Test
    fun testCommandsWithThreeScreens() {
        val app = MockApp()
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")
        val screenC = MockScreen("B")

        app.router.navigateTo(screenA)
        app.router.navigateTo(screenB)
        assertEquals(app.currentScreen, screenB)

        app.router.back()
        assertEquals(app.currentScreen, screenA)

        app.router.navigateTo(screenC)
        assertEquals(app.currentScreen, screenC)

        app.router.back()
        assertEquals(app.currentScreen, screenA)

        app.router.back()
        assertEquals(app.currentScreen, null)
    }


    @Test
    fun testRemoveAddNavigatorBeforeNavigationWithOneFragment() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")

        app.router.navigator = null
        app.router.navigateTo(screenA)
        assertEquals(app.currentScreen, null)

        app.router.navigator = navigator
        assertEquals(app.currentScreen, screenA)
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
        assertEquals(app.currentScreen, null)

        app.router.navigator = navigator
        assertEquals(app.currentScreen, screenB)
    }

    @Test
    fun testRemoveAddNavigatorDuringNavigationWithTwoFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        assertEquals(app.currentScreen, screenA)

        app.router.navigator = null
        assertEquals(app.currentScreen, screenA)

        app.router.navigateTo(screenB)
        assertEquals(app.currentScreen, screenA)

        app.router.navigator = navigator
        assertEquals(app.currentScreen, screenB)
    }

    @Test
    fun testRemoveAddNavigatorDuringNavigationWithThreeFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")
        val screenC = MockScreen("C")

        app.router.navigateTo(screenA)
        assertEquals(app.currentScreen, screenA)

        app.router.navigator = null
        assertEquals(app.currentScreen, screenA)

        app.router.navigateTo(screenB)
        assertEquals(app.currentScreen, screenA)

        app.router.navigator = navigator
        assertEquals(app.currentScreen, screenB)

        app.router.back()
        assertEquals(app.currentScreen, screenA)

        app.router.navigator = null
        assertEquals(app.currentScreen, screenA)

        app.router.navigator = navigator
        assertEquals(app.currentScreen, screenA)

        app.router.navigateTo(screenC)
        assertEquals(app.currentScreen, screenC)
    }

    @Test
    fun testRemoveAddNavigatorAfterNavigationWithTwoFragments() {
        val app = MockApp()
        val navigator = app.router.navigator!!
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")

        app.router.navigateTo(screenA)
        assertEquals(app.currentScreen, screenA)

        app.router.navigateTo(screenB)
        assertEquals(app.currentScreen, screenB)

        app.router.navigator = null
        assertEquals(app.currentScreen, screenB)

        app.router.back()
        assertEquals(app.currentScreen, screenB)

        app.router.navigator = navigator
        assertEquals(app.currentScreen, screenA)
    }


    @Test
    fun testChangeTab() {
        val app = MockApp(isMultipleTabs = true)
        val screenA = MockScreen("A")
        val screenB = MockScreen("B")
        val screenC = MockScreen("C")

        app.router.navigateTo(screenA)
        assertEquals(app.currentScreen, screenA)

        app.router.navigateTo(screenB)
        assertEquals(app.currentScreen, screenB)

        app.router.changeTab(MockTab.Second)
        app.router.navigateTo(screenC)
        assertEquals(app.currentScreen, screenC)

        app.router.changeTab(MockTab.First)
        assertEquals(app.currentScreen, screenB)
    }
}