package com.marcdenning.adventofcode.day11

import java.io.File

private const val FLOOR = '.'
private const val EMPTY = 'L'
private const val OCCUPIED = '#'

fun main(args: Array<String>) {
    val floorPlanMatrix = File(args[0]).readLines().map { it.toCharArray() }.toTypedArray()

    println("Number of occupied seats: ${countSeats(findSteadyFloorPlanByVisibility(floorPlanMatrix), OCCUPIED)}")

}

fun fillSeatsByVisibility(floorPlanMatrix: Array<CharArray>): Array<CharArray> {
    val outputFloorPlanMatrix = floorPlanMatrix.map { it.copyOf() }.toTypedArray()

    for ((rowIndex, row) in floorPlanMatrix.withIndex()) {
        for ((columnIndex, seat) in row.withIndex()) {
            if (seat == EMPTY && countVisibleSeats(floorPlanMatrix, rowIndex, columnIndex, OCCUPIED) == 0) {
                outputFloorPlanMatrix[rowIndex][columnIndex] = OCCUPIED
            }
        }
    }
    return outputFloorPlanMatrix
}

fun emptySeatsByVisibility(floorPlanMatrix: Array<CharArray>): Array<CharArray> {
    val outputFloorPlanMatrix = floorPlanMatrix.map { it.copyOf() }.toTypedArray()

    for ((rowIndex, row) in floorPlanMatrix.withIndex()) {
        for ((columnIndex, seat) in row.withIndex()) {
            if (seat == OCCUPIED && countVisibleSeats(floorPlanMatrix, rowIndex, columnIndex, OCCUPIED) >= 5) {
                outputFloorPlanMatrix[rowIndex][columnIndex] = EMPTY
            }
        }
    }
    return outputFloorPlanMatrix
}

fun findSteadyFloorPlanByVisibility(floorPlanMatrix: Array<CharArray>): Array<CharArray> {
    var lastFloorPlan = Array(floorPlanMatrix.size) { CharArray(floorPlanMatrix[0].size) }
    var currentFloorPlan = floorPlanMatrix.copyOf()
    var isFillTurn = true

    while (!lastFloorPlan.contentDeepEquals(currentFloorPlan)) {
        lastFloorPlan = currentFloorPlan
        if (isFillTurn) {
            currentFloorPlan = fillSeatsByVisibility(currentFloorPlan)
            isFillTurn = false
        } else {
            currentFloorPlan = emptySeatsByVisibility(currentFloorPlan)
            isFillTurn = true
        }
    }
    return currentFloorPlan
}


fun countVisibleSeats(floorPlanMatrix: Array<CharArray>, rowIndex: Int, columnIndex: Int, targetSeat: Char): Int {
    val directions = listOf(
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, 1),
        Pair(1, 1),
        Pair(1, 0),
        Pair(1, -1),
        Pair(0, -1),
        Pair(-1, -1)
    )
    var countOfSeats = 0

    for (directionPair in directions) {
        if (findVisibleSeatInDirection(floorPlanMatrix, rowIndex, columnIndex, directionPair.first, directionPair.second) == targetSeat) {
            countOfSeats++
        }
    }
    return countOfSeats
}

fun findVisibleSeatInDirection(floorPlanMatrix: Array<CharArray>, rowIndex: Int, columnIndex: Int, rowIncrement: Int, columnIncrement: Int): Char {
    if (rowIndex + rowIncrement < 0 || rowIndex + rowIncrement >= floorPlanMatrix.size ||
            columnIndex + columnIncrement < 0 || columnIndex + columnIncrement >= floorPlanMatrix[rowIndex].size) {
        return ' '
    }

    val cell = floorPlanMatrix[rowIndex + rowIncrement][columnIndex + columnIncrement]

    if (cell == EMPTY || cell == OCCUPIED) {
        return cell
    }
    return findVisibleSeatInDirection(floorPlanMatrix, rowIndex + rowIncrement, columnIndex + columnIncrement, rowIncrement, columnIncrement)
}
