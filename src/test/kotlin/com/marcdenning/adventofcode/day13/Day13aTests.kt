package com.marcdenning.adventofcode.day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day13aTests {

    @Test
    fun testFindNextDepartingBusAfterTime() {
        val departureTimestamp = 939L
        val busIdList = listOf(7, 13, 59, 31, 19)

        assertEquals(Pair(59, 944L), findNextDepartingBusAfterTime(departureTimestamp, busIdList))
    }

    @Test
    fun testFindEarliestTimeForBus() {
        val departureTimestamp = 939L

        assertEquals(945L, findEarliestTimeForBus(7, departureTimestamp))
        assertEquals(944L, findEarliestTimeForBus(59, departureTimestamp))
    }
}
