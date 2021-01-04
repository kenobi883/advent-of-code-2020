package com.marcdenning.adventofcode.day24

import com.marcdenning.adventofcode.day24.TileColor.WHITE
import com.marcdenning.adventofcode.day24.TileColor.BLACK

const val E = "e"
const val SE = "se"
const val SW = "sw"
const val W = "w"
const val NW = "nw"
const val NE = "ne"

fun main(args: Array<String>) {

}

fun parseInstructions(line: String): List<String> {
    val instructionList = mutableListOf<String>()
    var i = 0

    while (i < line.length) {
        when (line[i]) {
            's', 'n' -> {
                instructionList.add(line.subSequence(i, i + 2).toString())
                i++
            }
            else -> instructionList.add(line[i] + "")
        }
        i++
    }

    return instructionList
}

data class Tile(
    var color: TileColor = WHITE,
    var e: Tile = Tile(),
    var w: Tile = Tile(),
    var ne: Tile = Tile(),
    var nw: Tile = Tile(),
    var se: Tile = Tile(),
    var sw: Tile = Tile()
)

enum class TileColor {
    WHITE,
    BLACK
}
