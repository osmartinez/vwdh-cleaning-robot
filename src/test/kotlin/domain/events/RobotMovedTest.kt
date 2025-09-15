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
        robot.move()

        val events = robot.pullDomainEvents()
        assertTrue(events.any { it is RobotMoved && it.robotId == robot.id })
        assertTrue(robot.pullDomainEvents().isEmpty())
    }
}