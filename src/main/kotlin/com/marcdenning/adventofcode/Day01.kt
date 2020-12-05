package com.marcdenning.adventofcode

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val expenseList = File(args[0]).readLines().map { it -> it.toInt() }
    val numberOfOperands = args[1].toInt()

    for ((index, expense) in expenseList.withIndex()) {
        for (secondIndex in (index + 1) until expenseList.size) {
            val secondExpense = expenseList[secondIndex]

            if (numberOfOperands == 2 && 2020 == expense + secondExpense) {
                println("$expense + $secondExpense = ${expense + secondExpense}")
                println("$expense * $secondExpense = ${expense * secondExpense}")
                exitProcess(0)
            } else if (numberOfOperands == 3) {
                for (thirdIndex in (secondIndex + 1) until expenseList.size) {
                    val thirdExpense = expenseList[thirdIndex]

                    if (2020 == expense + secondExpense + thirdExpense) {
                        println("$expense + $secondExpense + $thirdExpense = ${expense + secondExpense + thirdExpense}")
                        println("$expense * $secondExpense * $thirdExpense = ${expense * secondExpense * thirdExpense}")
                        exitProcess(0)
                    }
                }
            }
        }
    }
    println("Could not find $numberOfOperands numbers that equal 2020")
    exitProcess(1)
}
