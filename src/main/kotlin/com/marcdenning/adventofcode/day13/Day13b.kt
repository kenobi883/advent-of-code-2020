package com.marcdenning.adventofcode.day13

import java.io.File

fun main(args: Array<String>) {
    val notes = File(args[0]).readLines()
    val busIdList = notes[1].split(',').map { if (it == "x") 0 else it.toInt() }
}

fun findEarliestTimestampForSubsequentDepartures(busIdList: List<Int>): Long {
    val earliestTimestamp = busIdList.max()!! - busIdList.size

    for (timestamp in earliestTimestamp..Long.MAX_VALUE) {
        val offsetList = busIdList.mapIndexed { index, busId -> if (busId == 0) timestamp + index else findEarliestTimeForBus(busId, timestamp) }
            .map { it - timestamp }

        if (offsetList
                .mapIndexed { index, offset -> offset == index.toLong() }
                .all { it }
        ) {
            return timestamp
        }
    }
    throw Exception()
}
