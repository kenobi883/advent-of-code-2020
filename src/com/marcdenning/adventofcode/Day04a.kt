package com.marcdenning.adventofcode

import java.io.File
import java.util.*
import java.util.regex.Pattern

private val AVAILABLE_FIELDS = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")
private val REQUIRED_FIELDS = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
private val PASSPORT_FIELD_PATTERN = Pattern.compile("(\\w{3}\\)\\:([\\w\\d\\#])+)")

fun main(args: Array<String>) {
    var numberOfValidPassports = 0

    Scanner(File(args[0])).use { scanner ->
        scanner.useDelimiter("\n\n")
        while (scanner.hasNext()) {
            val passportRecord = scanner.next()
            val passportFields = passportRecord.split(" ", "\n")
//            val passportFieldMatcher = PASSPORT_FIELD_PATTERN.matcher(passportRecord)
            val presentFields = mutableSetOf<String>()

            for (field in passportFields) {
                if (field != "") {
                    presentFields.add(field.substring(0, 3))
                }
            }
            if (presentFields.containsAll(REQUIRED_FIELDS)) {
                numberOfValidPassports++
            }
//            Scanner(passportRecord).use { passportScanner ->
//                val fieldsPresentSet = passportScanner.findAll(PASSPORT_FIELD_PATTERN).map { it.group(1) }.collect(Collectors.toSet())
//
//                if (fieldsPresentSet.containsAll(REQUIRED_FIELDS)) {
//                    numberOfValidPassports++
//                }
//            }
        }
    }
    println("Number of valid passports: $numberOfValidPassports")
}
