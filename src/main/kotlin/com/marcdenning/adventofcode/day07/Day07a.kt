package com.marcdenning.adventofcode.day07

import java.io.File
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors

fun main(args: Array<String>) {
    var numberOfColors = 0

    Scanner(File(args[0])).use { scanner ->
        numberOfColors += getAncestorsOfTargetColor(parseRules(scanner), args[1]).size
    }
    println("Number of colors: $numberOfColors")
}

/**
 * Extract map of color to rule pairings from a Scanner. Rules are a map of color to number of bags.
 */
fun parseRules(scanner: Scanner): Map<String, Map<String, Int>> = mapOf(
    *
    scanner.findAll("([\\w\\s]+)\\sbags\\scontain\\s([\\d\\s\\w\\,]+)\\.\\n?").map {
        Pair(it.group(1), parseRulesForColor(it.group(2)))
    }.collect(Collectors.toList()).toTypedArray()
)

fun parseRulesForColor(rulesString: String): Map<String, Int> {
    if ("no other bags" == rulesString) {
        return emptyMap()
    }
    return mapOf(
        *rulesString.split(',')
            .map {
                val matcher = Pattern.compile("(\\d+)\\s([\\w\\s]+)\\sbags?").matcher(it.trim())

                matcher.matches()
                Pair(matcher.group(2), matcher.group(1).toInt())
            }.toTypedArray()
    )
}

fun getAncestorsOfTargetColor(rules: Map<String, Map<String, Int>>, targetColor: String): Set<String> {
    // find colors that immediately contain the target color
    val immediateParentRules = rules.filter { entry -> entry.value.containsKey(targetColor) }
    // recurse through map for immediate parent colors to find their parents
    if (immediateParentRules.isEmpty()) {
        return emptySet()
    }
    return immediateParentRules.keys union immediateParentRules.map { getAncestorsOfTargetColor(rules, it.key) }
        .reduce { s1, s2 -> s1 union s2}
}
