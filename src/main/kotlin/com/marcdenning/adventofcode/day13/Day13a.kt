package com.marcdenning.adventofcode.day13

import java.io.File

fun main(args: Array<String>) {
    val notes = File(args[0]).readLines()
    val departureTime = notes[0].toLong()
    val busIdList = notes[1].split(',').filter { it != "x" }.map { it.toInt() }
    val busAndTimePair = findNextDepartingBusAfterTime(departureTime, busIdList)

    println("Bus ID: ${busAndTimePair.first}, Time: ${busAndTimePair.second}, Product: ${busAndTimePair.first * (busAndTimePair.second - departureTime)}")
}

fun findNextDepartingBusAfterTime(timestamp: Long, busIdList: List<Int>): Pair<Int, Long> {
    val busSchedules = mutableMapOf<Long, Int>()

    for (busId in busIdList) {
        busSchedules[findEarliestTimeForBus(busId, timestamp)] = busId
    }
    val earliestTimestamp = busSchedules.minOf { entry -> entry.key }
    return Pair(busSchedules[earliestTimestamp]!!, earliestTimestamp)
}

fun findEarliestTimeForBus(busId: Int, timestamp: Long): Long {
    var departureTimestamp = (timestamp / busId) * busId.toLong()

    if (departureTimestamp < timestamp) {
        departureTimestamp += busId
    }
    return departureTimestamp
}
