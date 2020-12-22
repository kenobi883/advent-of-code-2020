package com.marcdenning.adventofcode.day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.ArrayDeque

class Day22aTests {

    @Test
    fun testGetDeck() {
        val input = """
            Player 1:
            9
            2
            6
            3
            1

            Player 2:
            5
            8
            4
            7
            10
        """.trimIndent()

        assertTrue(arrayOf(9, 2, 6, 3, 1).contentEquals(getDeck(input.lines(), "1").toTypedArray()))
        assertTrue(arrayOf(5, 8, 4, 7, 10).contentEquals(getDeck(input.lines(), "2").toTypedArray()))
    }

    @Test
    fun testPlayRound() {
        val decks = Pair(ArrayDeque(listOf(9)), ArrayDeque(listOf(5)))

        playRound(decks)

        assertTrue(arrayOf(9, 5).contentEquals(decks.first.toTypedArray()))
        assertTrue(decks.second.isEmpty())
    }

    @Test
    fun testGetScore() {
        val deck = ArrayDeque(listOf(3, 2, 10, 6, 8, 5, 9, 4, 7, 1))

        assertEquals(306L, getScore(deck))

    }
}
