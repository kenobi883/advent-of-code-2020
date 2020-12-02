package com.marcdenning.adventofcode

import java.io.File
import java.util.*

fun main (args: Array<String>) {
    Scanner(File(args[0])).use { scanner ->
        val validPasswordStream = scanner.findAll("(\\d+)\\-(\\d+)\\s(\\w):\\s(\\w+)").filter { result ->
            val min = result.group(1).toInt()
            val max = result.group(2).toInt()
            val letter = result.group(3)[0]
            val password = result.group(4)

            password.count { c -> c == letter } in min..max
        }.peek { println(it.group(0)) }

        println("Number of valid passwords: ${validPasswordStream.count()}")
    }
}
