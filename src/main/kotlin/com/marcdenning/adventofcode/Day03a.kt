package com.marcdenning.adventofcode

import java.io.File

private const val OPEN_CHAR = '.'
private const val TREE_CHAR = '#'

fun main(args: Array<String>) {
    var xIndex = 0
    var yIndex = 0
    var numberOfTrees = 0

    File(args[0]).readLines().forEach { line ->
        if (yIndex != 0) {
            val lineLength = line.count()
            xIndex += 3
            if (xIndex >= lineLength) {
                xIndex -= lineLength
            }
            when (line[xIndex]) {
                TREE_CHAR -> numberOfTrees++
            }
        }
        yIndex++
    }
    println("Number of trees: $numberOfTrees")
}
