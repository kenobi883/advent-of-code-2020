package com.marcdenning.adventofcode.day07

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream


private val rulesString = """
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
    """.trimIndent()

class Day07Tests {

    @Test
    fun testParseRules() {
        var rules: Map<String, Map<String, Int>>

        Scanner(rulesString).use { scanner ->
            rules = parseRules(scanner)
        }

        assertEquals(9, rules.size)
        assertNotNull(rules["light red"])
        assertEquals(2, rules["light red"]!!.size)

        assertNotNull(rules["dotted black"])
        assertEquals(emptyMap<String, Int>(), rules["dotted black"]!!)
    }

    @Test
    fun testParseRulesForColor() {
        val expectedRules = mapOf(
            Pair(
                "1 shiny gold bag", mapOf(
                    Pair("shiny gold", 1)
                )
            ),
            Pair(
                "1 bright white bag, 2 muted yellow bags", mapOf(
                    Pair("bright white", 1),
                    Pair("muted yellow", 2)
                )
            ),
            Pair(
                "no other bags", emptyMap()
            )
        )

        for ((ruleString, expectedRulesMap) in expectedRules) {
            val actualRulesMap = parseRulesForColor(ruleString)

            assertEquals(expectedRulesMap.size, actualRulesMap.size)

            for ((expectedColor, expectedCount) in expectedRulesMap) {
                assertTrue(actualRulesMap.containsKey(expectedColor))
                assertEquals(expectedCount, actualRulesMap[expectedColor])
            }
        }
    }

    @ParameterizedTest
    @MethodSource("provideFullRuleSets")
    fun testGetAncestorsOfTargetColor(
        rulesString: String,
        targetColor: String,
        numberOfColors: Int,
        numberOfBags: Long
    ) {
        var rules: Map<String, Map<String, Int>>

        Scanner(rulesString).use { scanner ->
            rules = parseRules(scanner)
        }

        assertEquals(numberOfColors, getAncestorsOfTargetColor(rules, targetColor).size)
    }

    @ParameterizedTest
    @MethodSource("provideFullRuleSets")
    fun testNumberOfDescendantsOfTargetColor(
        rulesString: String,
        targetColor: String,
        numberOfColors: Int,
        numberOfBags: Long
    ) {
        var rules: Map<String, Map<String, Int>>

        Scanner(rulesString).use { scanner ->
            rules = parseRules(scanner)
        }

        assertEquals(numberOfBags, numberOfDescendantsOfTargetColor(rules, targetColor))
    }

    companion object {
        @JvmStatic
        fun provideFullRuleSets(): Stream<Arguments> = Stream.of(
            Arguments.of(
                rulesString, "shiny gold", 4, 32
            ),
            Arguments.of(
                """
                    shiny gold bags contain 2 dark red bags.
                    dark red bags contain 2 dark orange bags.
                    dark orange bags contain 2 dark yellow bags.
                    dark yellow bags contain 2 dark green bags.
                    dark green bags contain 2 dark blue bags.
                    dark blue bags contain 2 dark violet bags.
                    dark violet bags contain no other bags.
                """.trimIndent(), "shiny gold", 0, 126
            )
        )
    }
}
