package com.marcdenning.adventofcode

import java.io.File
import java.util.*

fun main (args: Array<String>) {
    Scanner(File(args[0])).use { scanner ->
        val validPasswordStream = scanner.findAll("(\\d+)\\-(\\d+)\\s(\\w):\\s(\\w+)").filter { result ->
            val firstIndex = result.group(1).toInt() - 1
            val secondIndex = result.group(2).toInt() - 1
            val letter = result.group(3)[0]
            val password = result.group(4)

            (password[firstIndex] == letter || password[secondIndex] == letter) &&
                    !(password[firstIndex] == letter && password[secondIndex] == letter)
        }.peek { println(it.group(0)) }

        println("Number of valid passwords: ${validPasswordStream.count()}")
    }
}
