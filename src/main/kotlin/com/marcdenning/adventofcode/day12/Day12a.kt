package com.marcdenning.adventofcode.day12

import java.io.File
import java.util.regex.Pattern
import kotlin.math.abs

const val FORWARD = 'F'
const val NORTH = 'N'
const val SOUTH = 'S'
const val EAST = 'E'
const val WEST = 'W'
const val RIGHT = 'R'
const val LEFT = 'L'

fun main(args: Array<String>) {
    var currentCoordinates = Coordinates(0, 0, 90)
    File(args[0]).readLines().map { parseInstruction(it) }
        .forEach { currentCoordinates = moveShipScalar(currentCoordinates, it) }

    println("Manhattan distance: ${abs(currentCoordinates.x) + abs(currentCoordinates.y)}")
}

fun parseInstruction(instruction: String): Pair<Char, Int> {
    val matcher = Pattern.compile("(\\w){1}(\\d+)").matcher(instruction)

    matcher.matches()
    return Pair(matcher.group(1)[0], matcher.group(2).toInt())
}

fun moveShipScalar(startingCoordinates: Coordinates, instruction: Pair<Char, Int>): Coordinates {
    return when (instruction.first) {
        FORWARD -> {
            return when (startingCoordinates.orientation) {
                0 -> Coordinates(startingCoordinates.x, startingCoordinates.y + instruction.second, startingCoordinates.orientation)
                90 -> Coordinates(startingCoordinates.x + instruction.second, startingCoordinates.y, startingCoordinates.orientation)
                180 -> Coordinates(startingCoordinates.x, startingCoordinates.y - instruction.second, startingCoordinates.orientation)
                270 -> Coordinates(startingCoordinates.x - instruction.second, startingCoordinates.y, startingCoordinates.orientation)
                else -> throw Exception()
            }
        }
        NORTH -> Coordinates(startingCoordinates.x, startingCoordinates.y + instruction.second, startingCoordinates.orientation)
        SOUTH -> Coordinates(startingCoordinates.x, startingCoordinates.y - instruction.second, startingCoordinates.orientation)
        EAST -> Coordinates(startingCoordinates.x + instruction.second, startingCoordinates.y, startingCoordinates.orientation)
        WEST -> Coordinates(startingCoordinates.x - instruction.second, startingCoordinates.y, startingCoordinates.orientation)
        RIGHT -> Coordinates(startingCoordinates.x, startingCoordinates.y, (startingCoordinates.orientation + instruction.second) % 360)
        LEFT -> {
            var newOrientation = startingCoordinates.orientation - instruction.second

            if (newOrientation < 0) {
                newOrientation += 360
            }
            Coordinates(startingCoordinates.x, startingCoordinates.y, newOrientation)
        }
        else -> throw Exception()
    }
}

data class Coordinates(
    val x: Long,
    val y: Long,
    val orientation: Int
)
