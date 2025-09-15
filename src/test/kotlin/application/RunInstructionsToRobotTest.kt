package application

import domain.Floor
import domain.InstructionList
import domain.Orientation
import domain.Position
import domain.Robot
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions.assertTrue;

private class FakeLogger: DomainLogger{
    val logs = mutableListOf<String>()
    override fun info(log: String) { logs += log}
}

class RunInstructionsToRobotTest {

    @Test
    fun `run instructions to robot`() {

        val logger = FakeLogger()

        val useCase = RunInstructionsToRobot(logger)
        val floor = Floor(5, 5)
        val robot = Robot(Position(3, 3), Orientation.E, floor)
        val instructionList = InstructionList.parse("MMRMMRMRRM")
        val robot1 = useCase.runRobot(floor, robot, instructionList)
        assertEquals(robot1.position, Position(5,1))
        assertEquals(robot1.orientation,Orientation.E)

        assertTrue(logger.logs.any { it.contains("Starting position: (3, 3)") })
        assertTrue(logger.logs.any { it.contains("Final position: (5, 1)") })
        assertTrue(logger.logs.any { it.contains("Final orientation: E") })
    }
}