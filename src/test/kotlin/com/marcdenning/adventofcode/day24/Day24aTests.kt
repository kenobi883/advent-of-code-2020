package com.marcdenning.adventofcode.day24

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class Day24aTests {

    @Test
    fun testParseInstructions() {
        val input = "esenee"
        val instructionList = parseInstructions(input)

        assertTrue(arrayOf(E, SE, NE, E).contentEquals(instructionList.toTypedArray()))
    }
}
