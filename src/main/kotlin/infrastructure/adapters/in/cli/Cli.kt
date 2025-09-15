package infrastructure.adapters.`in`.cli

import application.RunInstructionsToRobot
import infrastructure.adapters.out.InMemoryRobotStateRepository
import infrastructure.adapters.out.StdoutLogger

fun main() {
    val lines = generateSequence(::readLine).toList()
    val (floor, robots) = CliParser.parse(lines)
    val useCase = RunInstructionsToRobot(InMemoryRobotStateRepository(), StdoutLogger())
    robots.forEach { useCase.runRobot(floor, it.robot, it.instructions) }
}
