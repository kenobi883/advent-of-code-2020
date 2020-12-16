package com.marcdenning.adventofcode.day15

import java.util.*

fun main(args: Array<String>) {
    val startingNumbers = args[0].split(',').map { it.toInt() }
    val turnNumber = args[1].toInt()

    println("${turnNumber}th number spoken: ${getLastNumberSpoken(startingNumbers, turnNumber)}")
}

fun getLastNumberSpoken(startingNumbers: List<Int>, turnNumber: Int): Int {
    val mapOfLastIndexOfNumber = mutableMapOf<Int, Deque<Int>>()
    var lastNumber = startingNumbers.last()

    for ((index, num) in startingNumbers.withIndex()) {
        mapOfLastIndexOfNumber[num] = ArrayDeque(2)
        mapOfLastIndexOfNumber[num]!!.add(index)
    }
    for (i in startingNumbers.size until turnNumber) {
        lastNumber = getNextNumber(mapOfLastIndexOfNumber, lastNumber, i - 1)
        if (mapOfLastIndexOfNumber[lastNumber] == null) {
            mapOfLastIndexOfNumber[lastNumber] = ArrayDeque(2)
        }
        if (mapOfLastIndexOfNumber[lastNumber]!!.size == 2) {
            mapOfLastIndexOfNumber[lastNumber]!!.poll()
        }
        mapOfLastIndexOfNumber[lastNumber]!!.add(i)
    }
    return lastNumber
}

fun getNextNumber(mapOfLastIndexOfNumber: Map<Int, Deque<Int>>, lastNumber: Int, lastIndex: Int): Int {
    val previousIndexes = mapOfLastIndexOfNumber[lastNumber] ?: return 0

    if (previousIndexes.size == 1) {
        return 0
    }
    return lastIndex - previousIndexes.peekFirst()
}
