package application

import domain.Floor
import domain.InstructionList
import domain.Robot
import domain.ports.`in`.RunInstructionsToRobotUseCase

class RunInstructionsToRobot : RunInstructionsToRobotUseCase {
    override fun runRobot(
        floor: Floor,
        robot: Robot,
        instructions: InstructionList
    ): Robot {
        val robot = instructions.run(robot)
        return robot
    }
}