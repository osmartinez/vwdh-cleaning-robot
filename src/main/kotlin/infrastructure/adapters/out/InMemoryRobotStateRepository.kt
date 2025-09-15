package infrastructure.adapters.out

import domain.Robot
import domain.ports.out.RobotStateRepository

class InMemoryRobotStateRepository : RobotStateRepository {
    private val states = mutableListOf<Robot>()
    override fun save(robot: Robot) { states+= robot}
    override fun history(): List<Robot> = states.toList()
}