package com.marcdenning.adventofcode.day23

import java.util.Collections.*

fun main(args: Array<String>) {
    var cups = args[0].map { Integer.parseInt(it + "") }
    val moves = args[1].toInt()

    for (i in 1..moves) {
        cups = moveCups(cups)
    }

    println(getCupsStateString(cups))
}

/**
 * Conduct moves for 1 round:
 *
 * 1. Pick up 3 cups that are immediately clockwise of the current cup. They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
 * 2. Pick destination cup: the cup with a label equal to the current cup's label minus one. If this would select one of the cups that was just picked up, the crab will keep subtracting one until it finds a cup that wasn't just picked up. If at any point in this process the value goes below the lowest value on any cup's label, it wraps around to the highest value on any cup's label instead.
 * 3. Places the cups just picked up so that they are immediately clockwise of the destination cup. They keep the same order as when they were picked up.
 * 4. Select a new current cup: the cup which is immediately clockwise of the current cup.
 *
 * @param cups current state of cups, current cup is always index 0
 * @return new state of cups after a round
 */
fun moveCups(cups: List<Int>): List<Int> {
    val movedCups = mutableListOf(*cups.toTypedArray())
    val removedCups = mutableListOf<Int>()

    for (i in 1..3) {
        removedCups.add(movedCups.removeAt(1))
    }

    val destinationCupIndex = findDestinationCupIndex(movedCups)

    movedCups.addAll(destinationCupIndex + 1, removedCups)
    rotate(movedCups, -1)

    return movedCups
}

fun findDestinationCupIndex(cups: List<Int>): Int {
    val currentCup = cups[0]
    var destinationCup = currentCup - 1

    if (cups.contains(destinationCup)) {
        return cups.indexOf(destinationCup)
    }
    do {
        destinationCup--
        if (destinationCup < min(cups)) {
            destinationCup = max(cups)
        }
    } while (!cups.contains(destinationCup))
    return cups.indexOf(destinationCup)
}

fun getCupsStateString(cups: List<Int>): String {
    val assembledCups = mutableListOf(*cups.toTypedArray())
    val indexOf1 = assembledCups.indexOf(1)

    rotate(assembledCups, -indexOf1)
    assembledCups.remove(1)
    return assembledCups.joinToString(separator = "", transform = {i -> "" + i})
}
