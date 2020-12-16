package com.marcdenning.adventofcode.day16

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day16aTests {

    @Test
    fun testGetTicketScanningErrorRate() {
        val rules = listOf(
            Rule("class", 1..3, 5..7),
            Rule("row", 6..11, 33..44),
            Rule("seat", 13..40, 45..50)
        )
        val inputLines = listOf(
            "your ticket:",
            "7,1,14",
            "",
            "nearby tickets:",
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12"
        )

        assertEquals(71, getTicketScanningErrorRate(rules, inputLines))
    }

    @Test
    fun testParseRule() {
        val expectedRule = Rule(
            "departure location",
            48..885,
            906..949
        )
        val ruleString = "departure location: 48-885 or 906-949"
        val actualRule = parseRule(ruleString)

        assertEquals(expectedRule, actualRule)
    }

    @Test
    fun testRule_isNumberValid() {
        val rule = Rule(
            "departure location",
            48..885,
            906..949
        )

        assertTrue(rule.isNumberValid(48))
        assertTrue(rule.isNumberValid(50))
        assertTrue(rule.isNumberValid(885))
        assertTrue(rule.isNumberValid(906))
        assertTrue(rule.isNumberValid(949))
        assertFalse(rule.isNumberValid(950))
        assertFalse(rule.isNumberValid(905))
        assertFalse(rule.isNumberValid(886))
        assertFalse(rule.isNumberValid(47))
    }
}
