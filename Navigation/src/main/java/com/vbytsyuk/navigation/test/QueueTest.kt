package com.vbytsyuk.navigation.test

import containers.AriadneQueue
import containers.Queue
import junit.framework.Assert.*
import org.junit.Test

class QueueTest {
    @Test
    fun empty() {
        val queue: Queue<Int> = AriadneQueue()

        assertEquals(0, queue.size)
        assertTrue(queue.isEmpty())
        assertFalse(queue.isNotEmpty())
    }


    @Test
    fun addOne() {
        val queue: Queue<Int> = AriadneQueue()
        val value = 42
        queue.add(value)

        assertEquals(1, queue.size)
        assertFalse(queue.isEmpty())
        assertTrue(queue.isNotEmpty())
    }

    @Test
    fun pollOne() {
        val queue: Queue<Int> = AriadneQueue()
        val value = 42
        queue.add(value)
        val valueFromQueue = queue.poll()

        assertEquals(0, queue.size)
        assertTrue(queue.isEmpty())
        assertFalse(queue.isNotEmpty())
        assertEquals(value, valueFromQueue)
    }


    @Test
    fun addTwo() {
        val queue: Queue<Int> = AriadneQueue()
        val valueOne = 42
        val valueTwo = 23
        queue.add(valueOne)
        queue.add(valueTwo)

        assertEquals(2, queue.size)
        assertFalse(queue.isEmpty())
        assertTrue(queue.isNotEmpty())
    }


    @Test
    fun pollTwo() {
        val queue: Queue<Int> = AriadneQueue()
        val valueOne = 42
        val valueTwo = 23
        queue.add(valueOne)
        queue.add(valueTwo)
        val valueOneFromQueue = queue.poll()
        val valueTwoFromQueue = queue.poll()

        assertEquals(0, queue.size)
        assertTrue(queue.isEmpty())
        assertFalse(queue.isNotEmpty())
        assertEquals(valueOne, valueOneFromQueue)
        assertEquals(valueTwo, valueTwoFromQueue)
    }


    @Test
    fun addPoll() {
        val queue: Queue<Int> = AriadneQueue()
        val value = 42
        queue.add(value)

        assertEquals(1, queue.size)
        assertFalse(queue.isEmpty())
        assertTrue(queue.isNotEmpty())

        val valueFromQueue = queue.poll()

        assertEquals(0, queue.size)
        assertTrue(queue.isEmpty())
        assertFalse(queue.isNotEmpty())
        assertEquals(value, valueFromQueue)
    }
}