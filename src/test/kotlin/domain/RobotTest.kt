package domain
import domain.exceptions.DomainException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.assertThrows


class RobotTest {
    private val floor = Floor(5,5)

    @Test
    fun `rotations do not affect position`() {
        val robot = Robot(Position(1, 1), Orientation.N, floor)
        val rotatedRobot = robot.rotateClockwise()
        val rotatedAgainRobot = rotatedRobot.rotateAnticlockwise()
        assertEquals(Position(1,1), rotatedAgainRobot.position)
    }

    @Test
    fun `move forward inside floor limits`(){
        val robot = Robot(Position(1,1,), Orientation.N, floor).move()
        assertEquals(Position(1,2), robot.position)
    }

    @Test
    fun `move forward outside floor limits throws`(){
        val r = Robot(Position(0, 0), Orientation.S, floor)
        assertThrows<DomainException> { r.move() }
    }

    @Test
    fun `move forward outside N floor limits throws`(){
        val r = Robot(Position(0, floor.height), Orientation.N, floor)
        assertThrows<DomainException> { r.move() }
    }

    @Test
    fun `new generates unique ids`(){
        val a = RobotId.new()
        val b = RobotId.new()
        assertNotEquals(a,b)
    }

}