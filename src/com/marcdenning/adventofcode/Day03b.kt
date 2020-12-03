package com.marcdenning.adventofcode

import java.io.File
import java.math.BigInteger

private const val OPEN_CHAR = '.'
private const val TREE_CHAR = '#'

fun main(args: Array<String>) {
    val slope = File(args[0]).readLines()
    val paths = listOf(
            Pair(1, 1),
            Pair(3, 1),
            Pair(5, 1),
            Pair(7, 1),
            Pair(1, 2)
    )
    val multipleOfTrees = paths.map {
        val numberOfTrees = getNumberOfTreesForSlope(it.first, it.second, slope)

        println("Move: ${it.first} right, ${it.second} down; number of trees: $numberOfTrees")
        numberOfTrees
    }.reduce { acc, i -> acc * i }

    println("Multiple of trees encountered: $multipleOfTrees")
}

private fun getNumberOfTreesForSlope(xIncrement: Int, yIncrement: Int, slope: List<String>): Long {
    var xIndex = 0
    var yIndex = 0
    var numberOfTrees = 0

    while (yIndex < slope.size) {
        val line = slope[yIndex]
        if (yIndex != 0) {
            val lineLength = line.count()
            xIndex += xIncrement
            if (xIndex >= lineLength) {
                xIndex -= lineLength
            }
            when (line[xIndex]) {
                TREE_CHAR -> numberOfTrees++
            }
        }
        yIndex += yIncrement
    }
    return numberOfTrees.toLong()
}
