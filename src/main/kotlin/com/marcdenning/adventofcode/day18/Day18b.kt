package com.marcdenning.adventofcode.day18

import java.io.File

fun main(args: Array<String>) {
    val sum = File(args[0]).readLines().map { evaluateExpressionInverse(it) }.sum()

    println("Sum of all operations: $sum")
}

fun evaluateExpressionInverse(expression: String) = evaluateExpressionTree(buildTreeFromExpression(expression))

fun buildTreeFromExpression(expression: String): ExpressionNode {
    val expressionTree = ExpressionNode('+', ExpressionNode('0'))
    var currentNode = expressionTree

    for (char in expression) {
        if (char != ' ') {
            currentNode = currentNode.addChar(char)
        }
    }
    return expressionTree
}

fun evaluateExpressionTree(node: ExpressionNode): Long {
    if (node.left == null && node.right == null) {
        return ("" + node.char).toLong()
    }
    return when (node.char) {
        '+' -> evaluateExpressionTree(node.left!!) + evaluateExpressionTree(node.right!!)
        '*' -> evaluateExpressionTree(node.left!!) * evaluateExpressionTree(node.right!!)
        else -> throw Exception()
    }
}

data class ExpressionNode(
    var char: Char,
    var left: ExpressionNode? = null,
    var right: ExpressionNode? = null
) {
    fun addChar(newChar: Char): ExpressionNode {
        return when {
            char.isDigit() && (newChar == '+' || newChar == '*') -> {
                left = ExpressionNode(char)
                char = newChar
                this
            }
            (char == '+' || char == '*') && (newChar.isDigit() || newChar == '(') -> {
                right = ExpressionNode(newChar)
                right!!
            }
            char == '(' && newChar.isDigit() -> {
                left = ExpressionNode(newChar)
                this
            }
            char == '(' && (newChar == '+' || newChar == '*') -> {
                char = newChar
                this
            }
            newChar == ')' -> this
            else -> throw Exception()
        }
    }
}
