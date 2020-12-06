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
            val numberOfUniqueAnswers = group
                .filter { ("" + it).matches(Pattern.compile("\\w").toRegex()) }
                .toHashSet()
                .size

            totalAnswers += numberOfUniqueAnswers
        }
        println("Total unique answers: $totalAnswers")
    }
}
