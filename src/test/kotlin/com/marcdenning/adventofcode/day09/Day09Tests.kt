package com.marcdenning.adventofcode.day09

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day09Tests {

    @Test
    fun testFindFirstInvalidNumber() {
        val input = listOf(
            35L,
            20L,
            15L,
            25L,
            47L,
            40L,
            62L,
            55L,
            65L,
            95L,
            102L,
            117L,
            150L,
            182L,
            127L,
            219L,
            299L,
            277L,
            309L,
            576L
        )

        assertEquals(127L, findFirstInvalidNumber(5, input))
    }

    @Test
    fun testIsNumberValid() {
        val input = listOf(
            35L,
            20L,
            15L,
            25L,
            47L,
            40L,
            127L
        )

        assertTrue(isNumberValid(input.subList(0, 5), input[5]))
        assertFalse(isNumberValid(input.subList(1, 6), input[6]))
    }

    @Test
    fun testFindListOfOperandsToSumTo() {
        val input = listOf(
            35L,
            20L,
            15L,
            25L,
            47L,
            40L,
            62L,
            55L,
            65L,
            95L,
            102L,
            117L,
            150L,
            182L,
            127L,
            219L,
            299L,
            277L,
            309L,
            576L
        )

        val operands = findListOfOperandsToSumTo(127L, input)

        assertTrue(operands.contains(15L))
        assertTrue(operands.contains(25L))
        assertTrue(operands.contains(47L))
        assertTrue(operands.contains(40L))
    }
}
