package com.marcdenning.adventofcode.day16

import java.io.File
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val inputLines = File(args[0]).readLines()
    val separatorLineIndex = inputLines.indexOf("")
    val rules = inputLines.subList(0, separatorLineIndex).map { line ->
        parseRule(line)
    }

    println("Ticket scanning error rate: ${getTicketScanningErrorRate(rules, inputLines)}")
}

fun getTicketScanningErrorRate(rules: List<Rule>, inputLines: List<String>): Int {
    val nearbyTicketsLineIndex = inputLines.indexOf("nearby tickets:") + 1

    return inputLines.subList(nearbyTicketsLineIndex, inputLines.size).flatMap { ticket ->
        ticket.split(',').map { value -> value.toInt() }
    }.filter { value ->
        rules.all { rule -> !(rule as Rule).isNumberValid(value) }
    }.sum()
}

fun parseRule(ruleString: String): Rule {
    val matcher = Pattern.compile("([\\w\\s]+):\\s(\\d+)\\-(\\d+)\\sor\\s(\\d+)\\-(\\d+)").matcher(ruleString)

    if (!matcher.matches()) {
        throw Exception()
    }
    return Rule(
        matcher.group(1),
        matcher.group(2).toInt()..matcher.group(3).toInt(),
        matcher.group(4).toInt()..matcher.group(5).toInt()
    )
}

data class Rule(
    val fieldName: String,
    val range1: IntRange,
    val range2: IntRange
) {
    fun isNumberValid(int: Int): Boolean {
        return range1.contains(int) || range2.contains(int)
    }
}
