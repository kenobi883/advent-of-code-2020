package com.marcdenning.adventofcode.day07

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    var numberOfBags = 0L

    Scanner(File(args[0])).use { scanner ->
        numberOfBags += numberOfDescendantsOfTargetColor(parseRules(scanner), args[1])
    }
    println("Number of bags: $numberOfBags")
}

fun numberOfDescendantsOfTargetColor(rules: Map<String, Map<String, Int>>, targetColor: String): Long {
    // extract the child rules
    val immediateChildRules = rules[targetColor]
    // recurse through child rules to assemble all bag combinations and counts until a terminal state
    if (immediateChildRules.isNullOrEmpty()) {
        return 0L
    }
    return immediateChildRules.map { it.value + numberOfDescendantsOfTargetColor(rules, it.key) * it.value }
        .sum()
}

