package com.marcdenning.adventofcode.day05

import java.io.File
import java.util.*
import java.util.regex.MatchResult
import java.util.stream.Stream

private const val NUMBER_OF_ROWS = 128
private const val NUMBER_OF_COLUMNS = 8

fun main(args: Array<String>) {
    Scanner(File(args[0])).use { scanner ->
        val maxSeatId = scanner.findAll("[BF]{7}[LR]{3}")
            .map(::getSeatId)
            .max { o1, o2 -> o1 - o2 }
            .orElseThrow()

        println("Max seat ID: $maxSeatId")
    }
}

fun extractRow(seat: String) = seat.substring(0..6)

fun extractColumn(seat: String) = seat.substring(7..9)

fun determineRow(specifier: String, index: Int, lower: Int, upper: Int): Int {
    if (index == specifier.length - 1) {
        return if (specifier[index] == 'F') lower else upper
    } else if (specifier[index] == 'F') {
        return determineRow(specifier, index + 1, lower, lower + ((upper - lower) / 2))
    } else {
        return determineRow(specifier, index + 1, upper - ((upper - lower) / 2), upper)
    }
}

fun determineColumn(specifier: String, index: Int, lower: Int, upper: Int): Int {
    if (index == specifier.length - 1) {
        return if (specifier[index] == 'L') lower else upper
    } else if (specifier[index] == 'L') {
        return determineColumn(specifier, index + 1, lower, lower + ((upper - lower) / 2))
    } else {
        return determineColumn(specifier, index + 1, upper - ((upper - lower) / 2), upper)
    }
}

fun getSeatId(matchResult: MatchResult): Int {
    val rowId = extractRow(matchResult.group())
    val columnId = extractColumn(matchResult.group())

    return 8 * determineRow(rowId, 0, 0, NUMBER_OF_ROWS - 1) +
            determineColumn(columnId, 0, 0, NUMBER_OF_COLUMNS - 1)
}
