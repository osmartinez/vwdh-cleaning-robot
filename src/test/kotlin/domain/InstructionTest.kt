package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;
import kotlin.test.assertFailsWith

class InstructionTest {
    @Test
    fun `parse single instruction`(){
        assertEquals(Instruction.L, Instruction.parse("L"))
        assertEquals(Instruction.R, Instruction.parse("R"))
        assertEquals(Instruction.M, Instruction.parse("M"))
    }
}