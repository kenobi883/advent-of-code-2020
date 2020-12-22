package com.marcdenning.adventofcode.day22

import java.io.File
import java.util.Deque
import java.util.ArrayDeque


fun main(args: Array<String>) {
    val inputLines = File(args[0]).readLines()
    val decks = Pair(getDeck(inputLines, "1"), getDeck(inputLines, "2"))

    do {
        playRound(decks)
    } while (decks.first.isNotEmpty() && decks.second.isNotEmpty())

    if (decks.first.isEmpty()) {
        println(getScore(decks.second))
    } else {
        println(getScore(decks.first))
    }
}

fun getDeck(lines: List<String>, player: String): Deque<Int> {
    val playerLine = lines.indexOf("Player $player:")
    var deckLines = lines.subList(playerLine + 1, lines.size)

    deckLines = deckLines.subList(0, if (deckLines.indexOf("") == -1) deckLines.size else deckLines.indexOf(""))
    return ArrayDeque(deckLines.map { it.toInt() })
}

fun playRound(decks: Pair<Deque<Int>, Deque<Int>>): Pair<Deque<Int>, Deque<Int>> {
    val player1Card = decks.first.removeFirst()
    val player2Card = decks.second.removeFirst()

    if (player1Card > player2Card) {
        decks.first.addLast(player1Card)
        decks.first.addLast(player2Card)
    } else {
        decks.second.addLast(player2Card)
        decks.second.addLast(player1Card)
    }
    return decks
}

fun getScore(deck: Deque<Int>) =
    deck.mapIndexed { index, i -> (deck.size - index) * i }
        .map { it.toLong() }
        .sum()