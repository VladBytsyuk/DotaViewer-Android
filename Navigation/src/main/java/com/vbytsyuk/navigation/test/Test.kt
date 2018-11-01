package com.vbytsyuk.navigation.test

import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testOneScreen() {
        val app = MockApp()
        val screenA = MockScreen("A")

        app.router.navigateTo(screenA)
        assert(app.currentScreen == screenA)
        
        app.router.back()
        assert(app.currentScreen == null)
    }

    @Test
    fun testTwoScreens() {
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
    fun testThreeScreens() {
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
}