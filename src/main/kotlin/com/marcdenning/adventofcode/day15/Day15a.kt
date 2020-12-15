package com.marcdenning.adventofcode.day15

fun main(args: Array<String>) {
    val startingNumbers = args[0].split(',').map { it.toInt() }
    val turnNumber = args[1].toInt()

    println("${turnNumber}th number spoken: ${getLastNumberSpoken(startingNumbers, turnNumber)}")
}

fun getLastNumberSpoken(startingNumbers: List<Int>, turnNumber: Int): Int {
    val cumulativeNumbers = mutableListOf(*startingNumbers.toTypedArray())

    for (i in startingNumbers.size until turnNumber) {
        cumulativeNumbers.add(getNextNumber(cumulativeNumbers))
    }
    return cumulativeNumbers.last()
}

fun getNextNumber(cumulativeNumbers: List<Int>): Int {
    val lastNumber = cumulativeNumbers.last()
    val lastIndex = cumulativeNumbers.lastIndexOf(lastNumber)
    val previousIndex = cumulativeNumbers.subList(0, lastIndex).lastIndexOf(lastNumber)

    if (previousIndex == -1) {
        return 0
    }
    return lastIndex - previousIndex
}
