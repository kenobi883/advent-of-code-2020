package com.marcdenning.adventofcode.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Tests {

    // Part 1
    @Test
    fun testMoveShip_east() {
        val startingCoordinates = Coordinates(0, 0, 90)
        val instruction = Pair(EAST, 1)
        val forwardInstruction = Pair(FORWARD, 1)

        assertEquals(Coordinates(1, 0, 90), moveShipScalar(startingCoordinates, instruction))
        assertEquals(Coordinates(1, 0, 90), moveShipScalar(startingCoordinates, forwardInstruction))
    }

    @Test
    fun testMoveShip_south() {
        val startingCoordinates = Coordinates(0, 0, 90)
        val instruction = Pair(SOUTH, 1)

        assertEquals(Coordinates(0, -1, 90), moveShipScalar(startingCoordinates, instruction))
    }

    @Test
    fun testMoveShip_west() {
        val startingCoordinates = Coordinates(0, 0, 90)
        val instruction = Pair(WEST, 1)

        assertEquals(Coordinates(-1, 0, 90), moveShipScalar(startingCoordinates, instruction))
    }

    @Test
    fun testMoveShip_north() {
        val startingCoordinates = Coordinates(0, 0, 90)
        val instruction = Pair(NORTH, 1)

        assertEquals(Coordinates(0, 1, 90), moveShipScalar(startingCoordinates, instruction))
    }

    @Test
    fun testMoveShip_right() {
        val startingCoordinates = Coordinates(0, 0, 90)
        val instruction = Pair(RIGHT, 90)

        assertEquals(Coordinates(0, 0, 180), moveShipScalar(startingCoordinates, instruction))
    }

    @Test
    fun testMoveShip_left() {
        val startingCoordinates = Coordinates(0, 0, 90)
        val instruction = Pair(LEFT, 90)

        assertEquals(Coordinates(0, 0, 0), moveShipScalar(startingCoordinates, instruction))
    }

    // Part 2
    @Test
    fun testMoveShipToWaypoint_forward() {
        val ship = Ship(Coordinates(10, 10, 0), Coordinates(1, 1, 0))
        val instruction = Pair(FORWARD, 2)

        assertEquals(Coordinates(12, 12, 0), moveShipToWaypoint(ship, instruction).shipCoordinates)
        assertEquals(Coordinates(1, 1, 0), moveShipToWaypoint(ship, instruction).waypointCoordinates)
    }

    @Test
    fun testMoveShipToWaypoint_east() {
        val ship = Ship(Coordinates(0, 0, 0), Coordinates(1, 1, 0))
        val instruction = Pair(EAST, 1)

        assertEquals(Coordinates(0, 0, 0), moveShipToWaypoint(ship, instruction).shipCoordinates)
        assertEquals(Coordinates(2, 1, 0), moveShipToWaypoint(ship, instruction).waypointCoordinates)
    }

    @Test
    fun testMoveShipToWaypoint_south() {
        val ship = Ship(Coordinates(0, 0, 0), Coordinates(1, 1, 0))
        val instruction = Pair(SOUTH, 1)

        assertEquals(Coordinates(0, 0, 0), moveShipToWaypoint(ship, instruction).shipCoordinates)
        assertEquals(Coordinates(1, 0, 0), moveShipToWaypoint(ship, instruction).waypointCoordinates)
    }

    @Test
    fun testMoveShipToWaypoint_west() {
        val ship = Ship(Coordinates(0, 0, 0), Coordinates(1, 1, 0))
        val instruction = Pair(WEST, 1)

        assertEquals(Coordinates(0, 0, 0), moveShipToWaypoint(ship, instruction).shipCoordinates)
        assertEquals(Coordinates(0, 1, 0), moveShipToWaypoint(ship, instruction).waypointCoordinates)
    }

    @Test
    fun testMoveShipToWaypoint_north() {
        val ship = Ship(Coordinates(0, 0, 0), Coordinates(1, 1, 0))
        val instruction = Pair(NORTH, 1)

        assertEquals(Coordinates(0, 0, 0), moveShipToWaypoint(ship, instruction).shipCoordinates)
        assertEquals(Coordinates(1, 2, 0), moveShipToWaypoint(ship, instruction).waypointCoordinates)
    }

    @Test
    fun testMoveShipToWaypoint_left() {
        val ship = Ship(Coordinates(10, 10, 0), Coordinates(2, 1, 0))

        assertEquals(Coordinates(10, 10, 0), moveShipToWaypoint(ship, Pair(LEFT, 90)).shipCoordinates)
        assertEquals(Coordinates(-1, 2, 0), moveShipToWaypoint(ship, Pair(LEFT, 90)).waypointCoordinates)
        assertEquals(Coordinates(-2, -1, 0), moveShipToWaypoint(ship, Pair(LEFT, 180)).waypointCoordinates)
        assertEquals(Coordinates(1, -2, 0), moveShipToWaypoint(ship, Pair(LEFT, 270)).waypointCoordinates)
    }

    @Test
    fun testMoveShipToWaypoint_right() {
        val ship = Ship(Coordinates(10, 10, 0), Coordinates(2, 1, 0))

        assertEquals(Coordinates(10, 10, 0), moveShipToWaypoint(ship, Pair(RIGHT, 90)).shipCoordinates)
        assertEquals(Coordinates(1, -2, 0), moveShipToWaypoint(ship, Pair(RIGHT, 90)).waypointCoordinates)
        assertEquals(Coordinates(-2, -1, 0), moveShipToWaypoint(ship, Pair(RIGHT, 180)).waypointCoordinates)
        assertEquals(Coordinates(-1, 2, 0), moveShipToWaypoint(ship, Pair(RIGHT, 270)).waypointCoordinates)
    }
}

