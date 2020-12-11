package com.marcdenning.adventofcode.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day11Tests {

    @Test
    fun testFillSeats() {
        val initialFloorPlan = getFloorPlanMatrix(
            """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """
        )
        val expectedFloorPlan = getFloorPlanMatrix(
            """
            #.##.##.##
            #######.##
            #.#.#..#..
            ####.##.##
            #.##.##.##
            #.#####.##
            ..#.#.....
            ##########
            #.######.#
            #.#####.##
        """
        )
        val actualFloorPlan = fillSeats(initialFloorPlan)

        assertTrue(actualFloorPlan.contentDeepEquals(expectedFloorPlan))
    }

    @Test
    fun testEmptySeats() {
        val initialFloorPlan = getFloorPlanMatrix(
            """
            #.##.##.##
            #######.##
            #.#.#..#..
            ####.##.##
            #.##.##.##
            #.#####.##
            ..#.#.....
            ##########
            #.######.#
            #.#####.##
        """
        )
        val expectedFloorPlan = getFloorPlanMatrix(
            """
            #.LL.L#.##
            #LLLLLL.L#
            L.L.L..L..
            #LLL.LL.L#
            #.LL.LL.LL
            #.LLLL#.##
            ..L.L.....
            #LLLLLLLL#
            #.LLLLLL.L
            #.#LLLL.##
        """
        )

        assertTrue(expectedFloorPlan.contentDeepEquals(emptySeats(initialFloorPlan)))
    }

    @Test
    fun testFindSteadyFloorPlan() {
        val initialFloorPlan = getFloorPlanMatrix(
            """
            L.LL.LL.LL
            LLLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLLL
            L.LLLLLL.L
            L.LLLLL.LL
        """
        )
        val expectedFloorPlan = getFloorPlanMatrix(
            """
            #.#L.L#.##
            #LLL#LL.L#
            L.#.L..#..
            #L##.##.L#
            #.#L.LL.LL
            #.#L#L#.##
            ..L.L.....
            #L#L##L#L#
            #.LLLLLL.L
            #.#L#L#.##
        """
        )

        assertTrue(findSteadyFloorPlan(initialFloorPlan).contentDeepEquals(expectedFloorPlan))
    }

    @Test
    fun testCountSeats() {
        val finalFloorPlan = getFloorPlanMatrix(
            """
            #.#L.L#.##
            #LLL#LL.L#
            L.#.L..#..
            #L##.##.L#
            #.#L.LL.LL
            #.#L#L#.##
            ..L.L.....
            #L#L##L#L#
            #.LLLLLL.L
            #.#L#L#.##
        """
        )

        assertEquals(37, countSeats(finalFloorPlan, '#'))
    }

    private fun getFloorPlanMatrix(floorPlan: String) =
        floorPlan.trimIndent().split('\n').map { it.toCharArray() }.toTypedArray()
}
