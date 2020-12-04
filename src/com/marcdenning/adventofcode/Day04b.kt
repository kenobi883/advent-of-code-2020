package com.marcdenning.adventofcode

import java.io.File
import java.util.*
import java.util.regex.Pattern

private val AVAILABLE_FIELDS = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")
private val REQUIRED_FIELDS = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
private val PASSPORT_FIELD_PATTERN = Pattern.compile("(\\w{3})\\:([\\w\\d\\#]+)")

fun main(args: Array<String>) {
    var numberOfValidPassports = 0L

    Scanner(File(args[0])).use { scanner ->
        scanner.useDelimiter("\n\n")
        numberOfValidPassports = scanner.tokens().map { passportRecord ->
            val passportFields = passportRecord.split(" ", "\n")
            val presentFields = mutableSetOf<String>()

            for (field in passportFields) {
                val passportFieldMatcher = PASSPORT_FIELD_PATTERN.matcher(field)

                if (!passportFieldMatcher.matches() || passportFieldMatcher.groupCount() != 2) {
                    return@map false
                }

                val fieldKey = passportFieldMatcher.group(1)
                val fieldValue = passportFieldMatcher.group(2)

                presentFields.add(fieldKey)
                when (fieldKey) {
                    "byr" -> {
                        val birthYearMatcher = Pattern.compile("\\d{4}").matcher(fieldValue)

                        if (!birthYearMatcher.matches()) {
                            return@map false
                        } else if (birthYearMatcher.group().toInt() !in 1920..2002) {
                            return@map false
                        }
                    }
                    "iyr" -> {
                        val issueYearMatcher = Pattern.compile("\\d{4}").matcher(fieldValue)

                        if (!issueYearMatcher.matches()) {
                            return@map false
                        } else if (issueYearMatcher.group().toInt() !in 2010..2020) {
                            return@map false
                        }
                    }
                    "eyr" -> {
                        val issueYearMatcher = Pattern.compile("\\d{4}").matcher(fieldValue)

                        if (!issueYearMatcher.matches()) {
                            return@map false
                        } else if (issueYearMatcher.group().toInt() !in 2020..2030) {
                            return@map false
                        }
                    }
                    "hgt" -> {
                        val heightMatcher = Pattern.compile("(\\d+)(cm|in)").matcher(fieldValue)

                        if (!heightMatcher.matches()) {
                            return@map false
                        } else {
                            val unit = heightMatcher.group(2)
                            val height = heightMatcher.group(1).toInt()

                            when (unit) {
                                "cm" -> {
                                    if (height !in 150..193) {
                                        return@map false
                                    }
                                }
                                "in" -> {
                                    if (height !in 59..76) {
                                        return@map false
                                    }
                                }
                            }
                        }
                    }
                    "hcl" -> {
                        val hairColorMatcher = Pattern.compile("\\#[0-9a-f]{6}").matcher(fieldValue)

                        if (!hairColorMatcher.matches()) {
                            return@map false
                        }
                    }
                    "ecl" -> {
                        val eyeColorMatcher = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth").matcher(fieldValue)

                        if (!eyeColorMatcher.matches()) {
                            return@map false
                        }
                    }
                    "pid" -> {
                        val passportIdMatcher = Pattern.compile("\\d{9}").matcher(fieldValue)

                        if (!passportIdMatcher.matches()) {
                            return@map false
                        }
                    }
                }
            }
            presentFields.containsAll(REQUIRED_FIELDS)
        }.filter { it }.count()
    }
    println("Number of valid passports: $numberOfValidPassports")
}
