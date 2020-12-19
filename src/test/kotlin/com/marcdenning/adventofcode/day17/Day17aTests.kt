package com.marcdenning.adventofcode.day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day17aTests {

    private val initialPocketDimension = arrayOf("""
            .#.
            ..#
            ###
        """.trimIndent().lines().map { it.toCharArray() }.toTypedArray()
    )

    @Test
    fun testConductBootCycle() {
        val pocketDimension = conductBootCycle(initialPocketDimension)
        val expectedPocketDimension = arrayOf(
            arrayOf(
                "#..".toCharArray(),
                "..#".toCharArray(),
                ".#.".toCharArray()
            ),
            arrayOf(
                "#.#".toCharArray(),
                ".##".toCharArray(),
                ".#.".toCharArray()
            ),
            arrayOf(
                "#..".toCharArray(),
                "..#".toCharArray(),
                ".#.".toCharArray()
            )
        )

        printPocketDimension(pocketDimension)
        assertTrue(expectedPocketDimension.contentDeepEquals(pocketDimension))
    }

    @Test
    fun testGetAdjacentCubes() {
        val coordinates = Coordinates(1, 1, 0)
        val adjacentCubes = getAdjacentCubes(initialPocketDimension, coordinates)
        val expectedAdjacentCubes = arrayOf(
            arrayOf(
                "...".toCharArray(),
                "...".toCharArray(),
                "...".toCharArray()
            ),
            arrayOf(
                ".#.".toCharArray(),
                ". #".toCharArray(),
                "###".toCharArray()
            ),
            arrayOf(
                "...".toCharArray(),
                "...".toCharArray(),
                "...".toCharArray()
            )
        )

        printPocketDimension(adjacentCubes)
        assertTrue(expectedAdjacentCubes.contentDeepEquals(adjacentCubes))
    }

    @Test
    fun testCountCubesWithState() {
        assertEquals(5, countCubesWithState(initialPocketDimension, ACTIVE))
    }

    private fun printPocketDimension(pocketDimension: Array<Array<CharArray>>) {
        pocketDimension.forEach { plane ->
            plane.forEach(::println)
            println()
        }
    }
}
