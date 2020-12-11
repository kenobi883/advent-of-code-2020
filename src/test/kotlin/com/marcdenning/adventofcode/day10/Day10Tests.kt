package com.marcdenning.adventofcode.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10Tests {

    @Test
    fun testCountDifferencesOf() {
        val joltList = listOf(
            16,
            10,
            15,
            5,
            1,
            11,
            7,
            19,
            6,
            12,
            4
        )

        assertEquals(7, countDifferencesOf(joltList, 1))
        assertEquals(4, countDifferencesOf(joltList, 3))
    }
}
