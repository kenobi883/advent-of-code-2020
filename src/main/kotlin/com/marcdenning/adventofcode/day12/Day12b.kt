package com.marcdenning.adventofcode.day12

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    var ship = Ship(
        Coordinates(0, 0, 90),
        Coordinates(10, 1, 0)
    )

    File(args[0]).readLines().map { parseInstruction(it) }
        .forEach { ship = moveShipToWaypoint(ship, it) }

    println("Manhattan distance: ${abs(ship.shipCoordinates.x) + abs(ship.shipCoordinates.y)}")

}

fun moveShipToWaypoint(ship: Ship, instruction: Pair<Char, Int>): Ship {
    return when (instruction.first) {
        FORWARD -> {
            val xMovement = instruction.second * ship.waypointCoordinates.x
            val yMovement = instruction.second * ship.waypointCoordinates.y

            Ship(Coordinates(
                ship.shipCoordinates.x + xMovement,
                ship.shipCoordinates.y + yMovement,
                ship.shipCoordinates.orientation
            ), Coordinates(
                ship.waypointCoordinates.x,
                ship.waypointCoordinates.y,
                ship.waypointCoordinates.orientation
            ))
        }
        NORTH -> Ship(
            Coordinates(ship.shipCoordinates.x, ship.shipCoordinates.y, ship.shipCoordinates.orientation),
            Coordinates(ship.waypointCoordinates.x, ship.waypointCoordinates.y + instruction.second, ship.waypointCoordinates.orientation)
        )
        SOUTH -> Ship(
            Coordinates(ship.shipCoordinates.x, ship.shipCoordinates.y, ship.shipCoordinates.orientation),
            Coordinates(ship.waypointCoordinates.x, ship.waypointCoordinates.y - instruction.second, ship.waypointCoordinates.orientation)
        )
        EAST -> Ship(
            Coordinates(ship.shipCoordinates.x, ship.shipCoordinates.y, ship.shipCoordinates.orientation),
            Coordinates(ship.waypointCoordinates.x + instruction.second, ship.waypointCoordinates.y, ship.waypointCoordinates.orientation)
        )
        WEST -> Ship(
            Coordinates(ship.shipCoordinates.x, ship.shipCoordinates.y, ship.shipCoordinates.orientation),
            Coordinates(ship.waypointCoordinates.x - instruction.second, ship.waypointCoordinates.y, ship.waypointCoordinates.orientation)
        )
        LEFT -> when (instruction.second) {
            90 -> rotateWaypointLeft(ship)
            180 -> rotateWaypointLeft(rotateWaypointLeft(ship))
            270 -> rotateWaypointLeft(rotateWaypointLeft(rotateWaypointLeft(ship)))
            else -> throw Exception()
        }
        RIGHT -> when (instruction.second) {
            90 -> rotateWaypointRight(ship)
            180 -> rotateWaypointRight(rotateWaypointRight(ship))
            270 -> rotateWaypointRight(rotateWaypointRight(rotateWaypointRight(ship)))
            else -> throw Exception()
        }
        else -> throw Exception()
    }
}

fun rotateWaypointLeft(ship: Ship) = Ship(
    Coordinates(ship.shipCoordinates.x, ship.shipCoordinates.y, ship.shipCoordinates.orientation),
    Coordinates(-ship.waypointCoordinates.y, ship.waypointCoordinates.x, ship.waypointCoordinates.orientation)
)

fun rotateWaypointRight(ship: Ship) = Ship(
    Coordinates(ship.shipCoordinates.x, ship.shipCoordinates.y, ship.shipCoordinates.orientation),
    Coordinates(ship.waypointCoordinates.y, -ship.waypointCoordinates.x, ship.waypointCoordinates.orientation)
)

data class Ship(
    val shipCoordinates: Coordinates,
    val waypointCoordinates: Coordinates
)
