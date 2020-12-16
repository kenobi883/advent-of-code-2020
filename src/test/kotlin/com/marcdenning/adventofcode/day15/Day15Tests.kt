package com.marcdenning.adventofcode.day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class Day15Tests {

    @Test
    fun testGetNextNumber() {
        val numbersToIndexesMap = mutableMapOf<Int, Deque<Int>>()

        numbersToIndexesMap[0] = ArrayDeque(2)
        numbersToIndexesMap[0]!!.add(0)
        numbersToIndexesMap[3] = ArrayDeque(2)
        numbersToIndexesMap[3]!!.add(1)
        numbersToIndexesMap[6] = ArrayDeque(2)
        numbersToIndexesMap[6]!!.add(2)
        assertEquals(0, getNextNumber(numbersToIndexesMap, 6, 2))

        numbersToIndexesMap[0]!!.add(3)
        assertEquals(3, getNextNumber(numbersToIndexesMap, 0, 3))

        numbersToIndexesMap[3]!!.add(4)
        assertEquals(3, getNextNumber(numbersToIndexesMap, 3, 4))
    }

    @Test
    fun testGetLastNumberSpoken() {
        assertEquals(436, getLastNumberSpoken(listOf(0, 3, 6), 2020))
        assertEquals(1, getLastNumberSpoken(listOf(1, 3, 2), 2020))
        assertEquals(10, getLastNumberSpoken(listOf(2, 1, 3), 2020))
        assertEquals(1836, getLastNumberSpoken(listOf(3, 1, 2), 2020))
    }

    @Test
    fun testGetLastNumberSpoken_largeNumberOfTurns() {
        assertEquals(175594, getLastNumberSpoken(listOf(0, 3, 6), 30000000))
        assertEquals(6895259, getLastNumberSpoken(listOf(2, 3, 1), 30000000))
    }
}
