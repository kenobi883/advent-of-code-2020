package com.marcdenning.adventofcode.day11

import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

private const val FLOOR = '.'
private const val EMPTY = 'L'
private const val OCCUPIED = '#'

fun main(args: Array<String>) {
    val floorPlanMatrix = File(args[0]).readLines().map { it.toCharArray() }.toTypedArray()

    println("Number of occupied seats: ${countSeats(findSteadyFloorPlan(floorPlanMatrix), OCCUPIED)}")
}

fun fillSeats(floorPlanMatrix: Array<CharArray>): Array<CharArray> {
    val outputFloorPlanMatrix = floorPlanMatrix.map { it.copyOf() }.toTypedArray()

    for ((rowIndex, row) in floorPlanMatrix.withIndex()) {
        for ((columnIndex, seat) in row.withIndex()) {
            if (seat == EMPTY && countSeats(getAdjascentSeats(floorPlanMatrix, rowIndex, columnIndex), OCCUPIED) == 0) {
                outputFloorPlanMatrix[rowIndex][columnIndex] = OCCUPIED
            }
        }
    }
    return outputFloorPlanMatrix
}

fun emptySeats(floorPlanMatrix: Array<CharArray>): Array<CharArray> {
    val outputFloorPlanMatrix = floorPlanMatrix.map { it.copyOf() }.toTypedArray()

    for ((rowIndex, row) in floorPlanMatrix.withIndex()) {
        for ((columnIndex, seat) in row.withIndex()) {
            if (seat == OCCUPIED && countSeats(getAdjascentSeats(floorPlanMatrix, rowIndex, columnIndex), OCCUPIED) >= 4) {
                outputFloorPlanMatrix[rowIndex][columnIndex] = EMPTY
            }
        }
    }
    return outputFloorPlanMatrix
}

fun getAdjascentSeats(floorPlanMatrix: Array<CharArray>, rowIndex: Int, columnIndex: Int): Array<CharArray> {
    val startRow = max(0, rowIndex - 1)
    val startColumn = max(0, columnIndex - 1)
    val endRow = min(floorPlanMatrix.size - 1, rowIndex + 1)
    val endColumn = min(floorPlanMatrix[0].size - 1, columnIndex + 1)
    val adjascentSeatMatrix = floorPlanMatrix.copyOfRange(startRow, endRow + 1)
        .map { row -> row.copyOfRange(startColumn, endColumn + 1) }
        .toTypedArray()

    adjascentSeatMatrix[rowIndex - startRow][columnIndex - startColumn] = ' '
    return adjascentSeatMatrix
}

fun findSteadyFloorPlan(floorPlanMatrix: Array<CharArray>): Array<CharArray> {
    var lastFloorPlan = Array(floorPlanMatrix.size) { CharArray(floorPlanMatrix[0].size) }
    var currentFloorPlan = floorPlanMatrix.copyOf()
    var isFillTurn = true

    while (!lastFloorPlan.contentDeepEquals(currentFloorPlan)) {
        lastFloorPlan = currentFloorPlan
        if (isFillTurn) {
            currentFloorPlan = fillSeats(currentFloorPlan)
            isFillTurn = false
        } else {
            currentFloorPlan = emptySeats(currentFloorPlan)
            isFillTurn = true
        }
    }
    return currentFloorPlan
}

fun countSeats(floorPlanMatrix: Array<CharArray>, seatType: Char): Int =
    floorPlanMatrix.map { row -> row.map { seat -> if (seat == seatType) 1 else 0 }.sum() }.sum()
