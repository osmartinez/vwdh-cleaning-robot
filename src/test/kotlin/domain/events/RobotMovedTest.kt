package domain.events

import domain.Floor
import domain.Orientation
import domain.Position
import domain.Robot
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RobotMovedTest {
    val floor = Floor(5, 5)
    @Test
    fun `move produces events with source robot id`() {
        val robot = Robot.create(Position(0, 0), Orientation.N, floor)
        val robot1 = robot.move()
        val robot2 = robot1.move()

        val events = robot2.pullDomainEvents()
        assertTrue(events.size ==2 && events.all { it is RobotMoved && it.robotId == robot2.id })
        assertTrue(robot2.pullDomainEvents().isEmpty())
    }
}