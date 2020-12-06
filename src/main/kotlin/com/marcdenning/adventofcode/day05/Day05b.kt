package com.marcdenning.adventofcode.day05

import java.io.File
import java.util.*
import java.util.stream.Collectors
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    Scanner(File(args[0])).use { scanner ->
        val sortedSeatIds = scanner.findAll("[BF]{7}[LR]{3}")
            .map(::getSeatId)
            .sorted()
            .collect(Collectors.toList())

        for (i in 1 until sortedSeatIds.size) {
            val previousSeatId = sortedSeatIds[i - 1]
            val seatId = sortedSeatIds[i]

            if (seatId - previousSeatId == 2) {
                println("My seat ID: ${previousSeatId + 1}")
                exitProcess(0)
            }
        }
        exitProcess(1)
    }
}
