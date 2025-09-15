package infrastructure.adapters.`in`.cli

import domain.Floor
import domain.InstructionList
import domain.Orientation
import domain.Position
import domain.Robot

object CliParser {
    data class RobotInitialStateInput(val robot: Robot, val instructions: InstructionList)

    fun parse(lines: List<String>): Pair<Floor, List<RobotInitialStateInput>> {
        require(lines.isNotEmpty()) { "Empty CLI input" }

        val header = lines.first().trim().split(" ").filter{it.isNotEmpty()}
        require(header.size == 2) { "Invalid floor size input" }
        val floor = Floor(header[0].toInt(), header[1].toInt())

        val robotsData = lines.drop(1).map { it.trim() }.filter{ it.isNotEmpty()}
        val robots = mutableListOf<RobotInitialStateInput>()
        for( i in 0 until robotsData.size step 2){
            if (i + 1 >= robotsData.size) break
            val posParts = robotsData[i].split(" ").filter{it.isNotEmpty()}
            require(posParts.size == 3) { "Invalid robot position input" }
            val (xstr, ystr, ostr) = posParts
            val robot = Robot.create(Position(xstr.toInt(), ystr.toInt()), Orientation.valueOf(ostr), floor)
            val instructions = InstructionList.parse(robotsData[i+1])
            robots+= RobotInitialStateInput(robot, instructions)
        }
        return floor to robots
    }
}