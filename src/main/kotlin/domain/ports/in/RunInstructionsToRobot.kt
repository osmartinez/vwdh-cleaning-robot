package domain.ports.`in`

import domain.Floor
import domain.InstructionList
import domain.Robot

interface RunInstructionsToRobotUseCase {
    fun runRobot(floor: Floor, robot: Robot, instructions: InstructionList): Robot
}