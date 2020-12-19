package com.marcdenning.adventofcode.day18

import java.io.File

fun main(args: Array<String>) {
    val sum = File(args[0]).readLines().map { evaluateExpression(it) }.sum()

    println("Sum of all operations: $sum")
}

fun evaluateExpression(expression: String): Long {
    val expressionOperators = mutableListOf(' ')
    val expressionTotals = mutableListOf(0L)
    var expressionIndex = 0

    for (char in expression) {
        if (char.isDigit()) {
            when (expressionOperators[expressionIndex]) {
                ' ', '+' -> expressionTotals[expressionIndex] = expressionTotals[expressionIndex] + ("" + char).toInt()
                '*' -> expressionTotals[expressionIndex] = expressionTotals[expressionIndex] * ("" + char).toInt()
            }
        } else if (char == '*' || char == '+') {
            expressionOperators[expressionIndex] = char
        } else if (char == '(') {
            expressionTotals.add(0L)
            expressionOperators.add(' ')
            expressionIndex++
        } else if (char == ')') {
            val expressionTotal = expressionTotals.removeAt(expressionIndex)

            expressionOperators.removeAt(expressionIndex)
            expressionIndex--
            when (expressionOperators[expressionIndex]) {
                ' ', '+' -> expressionTotals[expressionIndex] = expressionTotals[expressionIndex] + expressionTotal
                '*' -> expressionTotals[expressionIndex] = expressionTotals[expressionIndex] * expressionTotal
            }
        }
    }
    return expressionTotals[0]
}
