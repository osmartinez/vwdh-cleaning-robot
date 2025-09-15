package domain
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;


class RobotTest {
    private val floor = Floor(5,5)

    @Test
    fun `rotations do not affect position`() {
        val robot = Robot(Position(1, 1), Orientation.N, floor)
        val rotatedRobot = robot.rotateClockwise()
        val rotatedAgainRobot = rotatedRobot.rotateAnticlockwise()
        assertEquals(Position(1,1), rotatedAgainRobot.position)
    }


}