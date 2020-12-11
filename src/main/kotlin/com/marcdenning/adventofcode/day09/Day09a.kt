package com.marcdenning.adventofcode.day09

import java.io.File

fun main(args: Array<String>) {
    val firstInvalidNumber = findFirstInvalidNumber(args[1].toInt(), File(args[0]).readLines().map { it.toLong() })

    println("First invalid number: $firstInvalidNumber")
}

fun findFirstInvalidNumber(sizeOfPreamble: Int, listOfNumbers: List<Long>): Long {
    for (i in sizeOfPreamble until listOfNumbers.size) {
        if (!isNumberValid(listOfNumbers.subList(i - sizeOfPreamble, i), listOfNumbers[i])) {
            return listOfNumbers[i]
        }
    }
    throw Exception()
}

fun isNumberValid(precedingNumbers: List<Long>, targetNumber: Long): Boolean {
    for ((i, num) in precedingNumbers.withIndex()) {
        for (j in (i + 1) until precedingNumbers.size) {
            if (num + precedingNumbers[j] == targetNumber) {
                return true
            }
        }
    }
    return false
}