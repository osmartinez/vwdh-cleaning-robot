package application

import domain.Floor
import domain.InstructionList
import domain.Robot
import domain.ports.`in`.RunInstructionsToRobotUseCase
import domain.ports.out.DomainLogger

class RunInstructionsToRobot(
    private val logger: DomainLogger
) : RunInstructionsToRobotUseCase {
    override fun runRobot(
        floor: Floor,
        robot: Robot,
        instructions: InstructionList
    ): Robot {
        logger.info("Starting position: (${robot.position.x}, ${robot.position.y})")
        val robot = instructions.run(robot)
        logger.info("Final position: (${robot.position.x}, ${robot.position.y})")
        logger.info("Final orientation: ${robot.orientation}")
        return robot
    }
}