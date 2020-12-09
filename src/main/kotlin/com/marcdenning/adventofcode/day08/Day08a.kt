package com.marcdenning.adventofcode.day08

import java.io.File
import java.util.regex.Pattern

private const val NOP = "nop"
private const val ACC = "acc"
private const val JMP = "jmp"

fun main(args: Array<String>) {
    val instructionList = File(args[0]).readLines()
    val accumulator = getAccumulatorValueAtRepeatedInstruction(instructionList)

    println("Value of accumulator at repeated instruction: $accumulator")
}

fun parseInstruction(instruction: String): Pair<String, Int> {
    val matcher = Pattern.compile("($NOP|$ACC|$JMP)\\s(\\+|\\-)(\\d+)").matcher(instruction)

    matcher.matches()
    return Pair(matcher.group(1), (matcher.group(2) + matcher.group(3)).toInt())
}

fun getAccumulatorValueAtRepeatedInstruction(instructionList: List<String>): Int {
    var accumulator = 0
    val instructionCountMap = mutableMapOf(*(0 until instructionList.size).toList().map { Pair(it, 0) }.toTypedArray())
    val instructionMap = mapOf(*instructionList.map { Pair(it, parseInstruction(it)) }.toTypedArray())
    var index = 0

    while (index < instructionList.size) {
        val instruction = instructionList[index]
        val instructionPair = instructionMap[instruction]!!

        if (instructionCountMap[index] == 1) {
            break
        }
        instructionCountMap[index] = instructionCountMap[index]!! + 1

        when (instructionPair.first) {
            NOP -> index++
            ACC -> {
                accumulator += instructionPair.second
                index++
            }
            JMP -> index += instructionPair.second
        }
    }
    return accumulator
}
