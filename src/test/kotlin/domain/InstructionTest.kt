package domain

import domain.exceptions.DomainException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.assertThrows


class InstructionTest {
    private val floor = Floor(5,5)

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

    @Test
    fun `run multiple separated instructions to robot`(){
        val robot1 = Robot(Position(0,0), Orientation.N, floor)
        val robot2 = Instruction.M.run(robot1)
        assertEquals(Position(0,1), robot2.position)
        val robot3 = Instruction.R.run(robot2)
        val robot4 = Instruction.M.run(robot3)
        assertEquals(Position(1,1), robot4.position)
    }

    @Test
    fun `run multiple instructions to robot`(){
        val instructionInput = "MRMMRMRMM"
        val instructionList = InstructionList.parse(instructionInput)
        val robot = Robot(Position(0,0), Orientation.N, floor)
        val robot2 = instructionList.run(robot)
        assertEquals(Position(0,0), robot2.position)
    }
}