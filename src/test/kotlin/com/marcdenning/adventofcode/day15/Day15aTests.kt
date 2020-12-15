package com.marcdenning.adventofcode.day15

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day15aTests {

    @Test
    fun testGetNextNumber() {
        assertEquals(0, getNextNumber(listOf(0, 3, 6)))
        assertEquals(3, getNextNumber(listOf(0, 3, 6, 0)))
        assertEquals(3, getNextNumber(listOf(0, 3, 6, 0, 3)))
    }

    @Test
    fun testGetLastNumberSpoken() {
        assertEquals(436, getLastNumberSpoken(listOf(0, 3, 6), 2020))
        assertEquals(1, getLastNumberSpoken(listOf(1, 3, 2), 2020))
        assertEquals(10, getLastNumberSpoken(listOf(2, 1, 3), 2020))
        assertEquals(1836, getLastNumberSpoken(listOf(3, 1, 2), 2020))
    }
}
