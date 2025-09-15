package application

import domain.Floor
import domain.InstructionList
import domain.Orientation
import domain.Position
import domain.Robot
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;

class RunInstructionsToRobotTest {

    @Test
    fun `run instructions to robot`() {
        val useCase = RunInstructionsToRobot()
        val floor = Floor(5, 5)
        val robot = Robot(Position(0, 0), Orientation.N, floor)
        val instructionList = InstructionList.parse("MMRMMRMRRM")
        val robot1 = useCase.runRobot(floor, robot, instructionList)
        assertEquals(robot1.position, Position(5,1))
        assertEquals(robot1.orientation,Orientation.E)

    }
}