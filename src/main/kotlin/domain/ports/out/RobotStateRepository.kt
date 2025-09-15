package domain.ports.out

import domain.Robot

interface RobotStateRepository {
    fun save(robot: Robot)
    fun history(): List<Robot>
}