package com.marcdenning.adventofcode.day05

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

private const val NUMBER_OF_ROWS = 128
private const val NUMBER_OF_COLUMNS = 8

class Day05aTests {

    @ParameterizedTest
    @MethodSource("provideSeatArguments")
    fun testDetermineRow(seat: String, row: Int, column: Int, seatId: Long) {
        Assertions.assertEquals(row, determineRow(extractRow(seat), 0, 0, NUMBER_OF_ROWS - 1))
    }

    @ParameterizedTest
    @MethodSource("provideSeatArguments")
    fun testDetermineColumn(seat: String, row: Int, column: Int, seatId: Long) {
        Assertions.assertEquals(column, determineColumn(extractColumn(seat), 0, 0, NUMBER_OF_COLUMNS - 1))
    }

    companion object {
        @JvmStatic
        fun provideSeatArguments(): Stream<Arguments> = Stream.of(
            Arguments.of("BFFFBBFRRR", 70, 7, 567L),
            Arguments.of("FFFBBBFRRR", 14, 7, 119L),
            Arguments.of("BBFFBBFRLL", 102, 4, 820L)
        )
    }
}