package com.marcdenning.adventofcode.day18

fun main(args: Array<String>) {

}

fun evaluateExpression(expression: String): Long {
    var total = 0L
    var lastOperator = ' '

    for (char in expression) {
        if (char.isDigit()) {
            when (lastOperator) {
                ' ', '+' -> total += ("" + char).toInt()
                '*' -> total *= ("" + char).toInt()
            }
        } else if (char == '*' || char == '+') {
            lastOperator = char
        }
    }
    return total
}
