package com.marcdenning.adventofcode.day13

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class Day13bTests {

    @Test
    fun testFindEarliestTimestampForSubsequentDepartures() {
        var millisToCompute = 0L

        millisToCompute = measureTimeMillis {
            assertEquals(3417L, findEarliestTimestampForSubsequentDepartures(listOf(17, 0, 13, 19)))
        }
        println("Milliseconds to compute 4 digit timestamp: $millisToCompute")
        millisToCompute = measureTimeMillis {
            assertEquals(754018L, findEarliestTimestampForSubsequentDepartures(listOf(67, 7, 59, 61)))
        }
        println("Milliseconds to compute 6 digit timestamp: $millisToCompute")
        millisToCompute = measureTimeMillis {
            assertEquals(779210L, findEarliestTimestampForSubsequentDepartures(listOf(67, 0, 7, 59, 61)))
        }
        println("Milliseconds to compute 6 digit timestamp: $millisToCompute")
        millisToCompute = measureTimeMillis {
            assertEquals(1261476L, findEarliestTimestampForSubsequentDepartures(listOf(67, 7, 0, 59, 61)))
        }
        println("Milliseconds to compute 7 digit timestamp: $millisToCompute")
        millisToCompute = measureTimeMillis {
            assertEquals(1202161486L, findEarliestTimestampForSubsequentDepartures(listOf(1789, 37, 47, 1889)))
        }
        println("Seconds to compute 10 digit timestamp: ${millisToCompute / 1000L}")
    }
}
