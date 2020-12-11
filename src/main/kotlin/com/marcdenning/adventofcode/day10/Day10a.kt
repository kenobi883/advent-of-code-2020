package com.marcdenning.adventofcode.day10

import java.io.File

fun main(args: Array<String>) {
    val joltList = File(args[0]).readLines().map { it.toInt() }
    val countOf1 = countDifferencesOf(joltList, 1)
    val countOf3 = countDifferencesOf(joltList, 3) + 1

    println("Product of number of jolt differences of 1 and 3: ${countOf1 * countOf3}")
}

fun countDifferencesOf(joltList: List<Int>, difference: Int): Int {
    var count = 0
    val sortedJoltList = joltList.sorted()

    for ((i, jolt) in sortedJoltList.withIndex()) {
        if (i == 0 && jolt == difference) {
            count++
        }
        if (i > 0 && jolt - sortedJoltList[i - 1] == difference) {
            count++
        }
    }
    return count
}
