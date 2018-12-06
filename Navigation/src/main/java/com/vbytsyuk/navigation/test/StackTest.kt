package com.vbytsyuk.navigation.test

import containers.AriadneStack
import containers.Stack
import junit.framework.Assert.*
import org.junit.Test

class StackTest {
    @Test
    fun empty() {
        val stack: Stack<Int> = AriadneStack()

        assertEquals(0, stack.size)
        assertTrue(stack.isEmpty())
        assertFalse(stack.isNotEmpty())
    }


    @Test
    fun pushOne() {
        val stack: Stack<Int> = AriadneStack()
        val value = 42
        stack.push(value)

        assertEquals(1, stack.size )
        assertFalse(stack.isEmpty())
        assertTrue(stack.isNotEmpty())
    }

    @Test
    fun popOne() {
        val stack: Stack<Int> = AriadneStack()
        val value = 42
        stack.push(value)
        val valueFromStack = stack.pop()

        assertEquals(0, stack.size)
        assertTrue(stack.isEmpty())
        assertFalse(stack.isNotEmpty())
        assertEquals(value, valueFromStack)
    }

    @Test
    fun peekOne() {
        val stack: Stack<Int> = AriadneStack()
        val value = 42
        stack.push(value)
        val valueFromStack = stack.peek()

        assertEquals(1, stack.size)
        assertFalse(stack.isEmpty())
        assertTrue(stack.isNotEmpty())
        assertEquals(value, valueFromStack)
    }


    @Test
    fun pushTwo() {
        val stack: Stack<Int> = AriadneStack()
        val valueOne = 42
        val valueTwo = 23
        stack.push(valueOne)
        stack.push(valueTwo)

        assertEquals(2, stack.size )
        assertFalse(stack.isEmpty())
        assertTrue(stack.isNotEmpty())
    }


    @Test
    fun popTwo() {
        val stack: Stack<Int> = AriadneStack()
        val valueOne = 42
        val valueTwo = 23
        stack.push(valueOne)
        stack.push(valueTwo)
        val valueTwoFromStack = stack.pop()
        val valueOneFromStack = stack.pop()

        assertEquals(0, stack.size)
        assertTrue(stack.isEmpty())
        assertFalse(stack.isNotEmpty())
        assertEquals(valueTwo, valueTwoFromStack)
        assertEquals(valueOne, valueOneFromStack)
    }


    @Test
    fun pushPeekPop() {
        val stack: Stack<Int> = AriadneStack()
        val value = 42
        stack.push(value)

        assertEquals(1, stack.size )
        assertFalse(stack.isEmpty())
        assertTrue(stack.isNotEmpty())

        val peekedValueFromStack = stack.peek()

        assertEquals(1, stack.size)
        assertFalse(stack.isEmpty())
        assertTrue(stack.isNotEmpty())
        assertEquals(value, peekedValueFromStack)

        val poppedValueFromStack = stack.pop()

        assertEquals(0, stack.size)
        assertTrue(stack.isEmpty())
        assertFalse(stack.isNotEmpty())
        assertEquals(value, poppedValueFromStack)
    }
}