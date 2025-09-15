package domain

import domain.exceptions.DomainException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.assertThrows


class InstructionTest {
    @Test
    fun `parse single instruction`(){
        assertEquals(Instruction.L, Instruction.parse('L'))
        assertEquals(Instruction.R, Instruction.parse('R'))
        assertEquals(Instruction.M, Instruction.parse('M'))
    }


    @Test
    fun `parse invalid instruction`(){
        assertThrows<DomainException> { Instruction.parse('X') }
    }
}