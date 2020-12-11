package com.marcdenning.adventofcode.day09

import java.io.File

fun main(args: Array<String>) {
    val listOfOperands = findListOfOperandsToSumTo(args[1].toLong(), File(args[0]).readLines().map { it.toLong() })
    val minOperand = listOfOperands.minOrNull()!!
    val maxOperand = listOfOperands.maxOrNull()!!

    println("Sum of smallest and largest operands: ${minOperand + maxOperand}")
}

fun findListOfOperandsToSumTo(targetSum: Long, listOfNumbers: List<Long>): List<Long> {
    var runningSum = 0L

    for (i in 0 until listOfNumbers.size) {
        runningSum += listOfNumbers[i]
        if (runningSum == targetSum) {
            return listOfNumbers.subList(0, i + 1)
        }
        if (runningSum > targetSum) {
            return findListOfOperandsToSumTo(targetSum, listOfNumbers.subList(1, listOfNumbers.size))
        }
    }
    throw IllegalStateException()
}
