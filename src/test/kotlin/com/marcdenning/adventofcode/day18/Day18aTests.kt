package com.marcdenning.adventofcode.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day18aTests {

    @Test
    fun testEvaluateExpression() {
        assertEquals(71, evaluateExpression("1 + 2 * 3 + 4 * 5 + 6"))
        assertEquals(51, evaluateExpression("1 + (2 * 3) + (4 * (5 + 6))"))
        assertEquals(26, evaluateExpression("2 * 3 + (4 * 5)"))
        assertEquals(437, evaluateExpression("5 + (8 * 3 + 9 + 3 * 4 * 3)"))
        assertEquals(12240, evaluateExpression("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"))
        assertEquals(13632, evaluateExpression("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
    }
}
