package com.marcdenning.adventofcode.day21

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day21bTests {

    @Test
    fun testGetDangerousIngredientsList() {
        assertEquals(listOf("mxmxvkd", "sqjhc", "fvjkl"), getDangerousIngredientsList(foods))
    }
}
