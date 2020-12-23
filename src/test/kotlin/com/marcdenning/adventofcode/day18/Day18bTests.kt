package com.marcdenning.adventofcode.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day18bTests {

    @Test
    fun testEvaluateExpressionInverse() {
        assertEquals(231L, evaluateExpressionInverse("1 + 2 * 3 + 4 * 5 + 6"))
        assertEquals(46L, evaluateExpressionInverse("2 * 3 + (4 * 5)"))
        assertEquals(51L, evaluateExpressionInverse("1 + (2 * 3) + (4 * (5 + 6))"))
        assertEquals(1445L, evaluateExpressionInverse("5 + (8 * 3 + 9 + 3 * 4 * 3)"))
        assertEquals(669060L, evaluateExpressionInverse("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"))
        assertEquals(23340L, evaluateExpressionInverse("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"))
    }

    @Test
    fun testBuildTreeFromExpression() {
        assertEquals(ExpressionNode('+', ExpressionNode('0'), ExpressionNode('3')), buildTreeFromExpression("3"))
        assertEquals(
            ExpressionNode(
                '+',
                ExpressionNode('0'),
                ExpressionNode('+', ExpressionNode('1'), ExpressionNode('2'))
            ), buildTreeFromExpression("1 + 2")
        )
        assertEquals(
            ExpressionNode(
                '+', ExpressionNode('0'), ExpressionNode(
                    '+',
                    ExpressionNode('+', ExpressionNode('1'), ExpressionNode('2')),
                    ExpressionNode('3')
                )
            ), buildTreeFromExpression("(1 + 2) + 3")
        )
        assertEquals(
            ExpressionNode(
                '+', ExpressionNode('0'), ExpressionNode(
                    '*',
                    ExpressionNode('+', ExpressionNode('1'), ExpressionNode('2')),
                    ExpressionNode('3')
                )
            ), buildTreeFromExpression("1 + 2 * 3")
        )
    }

    @Test
    fun testEvaluateExpressionTree() {
        assertEquals(3, evaluateExpressionTree(ExpressionNode('3')))
        assertEquals(3, evaluateExpressionTree(ExpressionNode('+', ExpressionNode('1'), ExpressionNode('2'))))
        assertEquals(6, evaluateExpressionTree(ExpressionNode('*', ExpressionNode('2'), ExpressionNode('3'))))
        assertEquals(
            6, evaluateExpressionTree(
                ExpressionNode(
                    '+',
                    ExpressionNode('+', ExpressionNode('1'), ExpressionNode('2')),
                    ExpressionNode('3')
                )
            )
        )
    }
}
