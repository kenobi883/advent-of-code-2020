package com.marcdenning.adventofcode.day06

import java.io.File
import java.util.*
import java.util.regex.Pattern

fun main(args: Array<String>) {
    Scanner(File(args[0])).use { scanner ->
        var totalAnswers = 0

        scanner.useDelimiter("\n\n")

        while (scanner.hasNext()) {
            val group = scanner.next()
            val listOfSetsOfAnswers = mutableListOf<Set<Char>>()

            Scanner(group).use { groupScanner ->
                groupScanner.useDelimiter("\n")

                while (groupScanner.hasNext()) {
                    listOfSetsOfAnswers.add(groupScanner.next().toSet())
                }
            }

            totalAnswers += listOfSetsOfAnswers.reduce { acc, set -> acc intersect set }.size
        }
        println("Total unique answers: $totalAnswers")
    }
}
