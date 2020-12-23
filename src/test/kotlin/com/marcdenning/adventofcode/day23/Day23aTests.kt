package com.marcdenning.adventofcode.day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day23aTests {

    @Test
    fun testMoveCups() {
        var cups = listOf(3, 8, 9, 1, 2, 5, 4, 6, 7)

        cups = moveCups(cups)
        assertTrue(listOf(2, 8, 9, 1, 5, 4, 6, 7, 3).toTypedArray().contentDeepEquals(cups.toTypedArray()))
        cups = moveCups(cups)
        assertTrue(listOf(5, 4, 6, 7, 8, 9, 1, 3, 2).toTypedArray().contentDeepEquals(cups.toTypedArray()))
    }

    @Test
    fun testGetCupsStateString() {
        assertEquals("92658374", getCupsStateString(listOf(8, 3, 7, 4, 1, 9, 2, 6, 5)))
        assertEquals("25467389", getCupsStateString(listOf(3, 8, 9, 1, 2, 5, 4, 6, 7)))
    }
}
