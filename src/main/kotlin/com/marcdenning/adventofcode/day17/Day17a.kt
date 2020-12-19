package com.marcdenning.adventofcode.day17

import java.io.File

const val ACTIVE = '#'
const val INACTIVE = '.'

fun main(args: Array<String>) {
    val numberOfBootCycles = args[1].toInt()
    var pocketDimension = arrayOf(File(args[0]).readLines().map { it.toCharArray() }.toTypedArray())

    for (i in 1..numberOfBootCycles) {
        pocketDimension = conductBootCycle(pocketDimension)
    }
    println(
        "Number of active cubes after $numberOfBootCycles boot cycles: ${
            countCubesWithState(
                pocketDimension,
                ACTIVE
            )
        }"
    )
}

/**
 * Execute a single boot cycle. Each cube simultaneously changes state according to rules:
 *
 * 1. If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
 * 2. If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
 *
 * @return new matrix of cube states, will grow continuously
 */
fun conductBootCycle(pocketDimension: Array<Array<CharArray>>): Array<Array<CharArray>> {
    val expandedPocketDimension = Array(pocketDimension.size + 2) { Array(pocketDimension[0].size + 2) { CharArray(pocketDimension[0][0].size + 2) { INACTIVE } } }

    // Copy existing dimension map into expanded map
    pocketDimension.forEachIndexed { planeIndex, plane ->
        plane.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { charIndex, char ->
                expandedPocketDimension[planeIndex + 1][rowIndex + 1][charIndex + 1] = char
            }
        }
    }

    // Apply active/inactive rules
    expandedPocketDimension.forEachIndexed { planeIndex, plane ->
        plane.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { charIndex, char ->
                val countOfActiveAdjacentCubes = countCubesWithState(
                    getAdjacentCubes(expandedPocketDimension, Coordinates(charIndex, rowIndex, planeIndex)),
                    ACTIVE
                )

                if (char == ACTIVE && countOfActiveAdjacentCubes !in 2..3) {
                    expandedPocketDimension[planeIndex][rowIndex][charIndex] = INACTIVE
                } else if (char == INACTIVE && countOfActiveAdjacentCubes == 3) {
                    expandedPocketDimension[planeIndex][rowIndex][charIndex] = ACTIVE
                }
            }
        }
    }

    // Trim completely inactive edges

    return expandedPocketDimension
}

/**
 * Get all cubes adjacent to given coordinates. This is 26 cubes, with each coordinate value offset by 1.
 *
 * @return matrix of adjascent cubes, including target coordinates as space character
 */
fun getAdjacentCubes(pocketDimension: Array<Array<CharArray>>, coordinates: Coordinates): Array<Array<CharArray>> {
    val adjacentCubes = Array(3) { Array(3) { CharArray(3) { INACTIVE } } }

    for (z in -1..1) {
        for (y in -1..1) {
            for (x in -1..1) {
                if (z == 0 && y == 0 && x == 0) {
                    adjacentCubes[1][1][1] = ' '
                } else if (coordinates.z + z !in pocketDimension.indices ||
                    coordinates.y + y !in pocketDimension[coordinates.z + z].indices ||
                    coordinates.x + x !in pocketDimension[coordinates.z + z][coordinates.y + y].indices
                ) {
                    adjacentCubes[z + 1][y + 1][x + 1] = INACTIVE
                } else {
                    adjacentCubes[z + 1][y + 1][x + 1] =
                        pocketDimension[coordinates.z + z][coordinates.y + y][coordinates.x + x]
                }
            }
        }
    }

    return adjacentCubes
}

fun countCubesWithState(pocketDimension: Array<Array<CharArray>>, cubeState: Char) =
    pocketDimension.map { plane ->
        plane.map { row -> row.map { cube -> if (cube == cubeState) 1 else 0 }.sum() }.sum()
    }.sum()

data class Coordinates(
    val x: Int,
    val y: Int,
    val z: Int
)