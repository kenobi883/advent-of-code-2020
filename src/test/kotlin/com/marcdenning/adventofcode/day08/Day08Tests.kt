package com.marcdenning.adventofcode.day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day08Tests {

    @ParameterizedTest
    @MethodSource("provideInstructions")
    fun testParseInstruction(instruction: String, command: String, increment: Int) {
        val instructionPair = parseInstruction(instruction)

        assertEquals(command, instructionPair.first)
        assertEquals(increment, instructionPair.second)
    }

    @ParameterizedTest
    @MethodSource("provideFullProgram")
    fun testGetAccumulatorValueAtRepeatedInstruction(program: String, targetAccumulator: Int) {
        assertEquals(targetAccumulator, getAccumulatorValueAtRepeatedInstruction(program.lines()))
    }

    companion object {
        @JvmStatic
        fun provideInstructions(): Stream<Arguments> = Stream.of(
            Arguments.of("nop +0", "nop", 0),
            Arguments.of("acc +12", "acc", 12),
            Arguments.of("jmp -3", "jmp", -3)
        )

        @JvmStatic
        fun provideFullProgram(): Stream<Arguments> = Stream.of(
            Arguments.of(
                """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent(), 5
            ),
            Arguments.of(
                """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            nop -4
            acc +6
        """.trimIndent(), 8
            )
        )
    }
}
